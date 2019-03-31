package com.kalay.shift.shift.Activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kalay.shift.shift.Classes.AlertPublisher;
import com.kalay.shift.shift.Classes.AlertsSaver;
import com.kalay.shift.shift.R;
import com.mcsoft.timerangepickerdialog.RangeTimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.kalay.shift.shift.Activities.MainActivity.CHANNEL_ID;

/**
 * Created by romdolinger on 4/14/18.
 */

public class AddPersonalTimeActivity extends Activity implements RangeTimePickerDialog.ISelectedTime {

    EditText alertTitle = new EditText(getApplicationContext());
    EditText alertContent = new EditText(getApplicationContext());
    CheckBox[] daysArr = new CheckBox[7];
    Button saveAlert = new Button(getApplicationContext());


    int i = AlertsSaver.startKey;
    static final String names[] = {"ראשון", "שני", "שלישי", "רביעי", "חמישי", "שישי", "שבת"};
    Spinner dropdown;
    List<Integer> keyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_time);

        alertTitle = (EditText) findViewById(R.id.alertTitle);
        alertContent = (EditText) findViewById(R.id.alertContent);
        daysArr[0] = (CheckBox) findViewById(R.id.chbSunday);
        daysArr[1] = (CheckBox) findViewById(R.id.chbMonday);
        daysArr[2] = (CheckBox) findViewById(R.id.chbTuesday);
        daysArr[3] = (CheckBox) findViewById(R.id.chbWednesday);
        daysArr[4] = (CheckBox) findViewById(R.id.chbThursday);
        daysArr[5] = (CheckBox) findViewById(R.id.chbFriday);
        daysArr[6] = (CheckBox) findViewById(R.id.chbSaturday);
        saveAlert = (Button) findViewById(R.id.addAlert);








        //create a list of items for the spinner.
        //List<Object> myList = new ArrayList<>();
        //myList.add("Please select an Item");
        //List<String> deleted = AlertsSaver.returnDeltedPlaces(this);
        //AlertsSaver alert;
        /*This loops add all the keys of the valid alerts into keyList.
          in the end of the loop, i points to the next free key.
        */
//        while (true) {
//            try {
//                //
//                if (deleted.size() == 0 || (deleted.size() != 0 && !deleted.contains(getString(i)))) {
//                    alert = new AlertsSaver(this, Integer.toString(i));
//                    myList.add(alert.getAlertTitle());
//                    keyList.add(i);
//                }
//                i++;
//            } catch (Exception e) {
//                break;
//            }
//        }

        //read input array
//        for (int j = 0; j < names.length; j++) {
//            //create the UI check box
//            final LinearLayout ll = findViewById(R.id.linearLayoutId);
//            CheckBox cb = new CheckBox(getApplicationContext());
//            cb.setText(names[j]);
//            ll.addView(cb);
//            daysArr[j] = cb;
//        }
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.

        //dropdown = findViewById(R.id.spinner);
        //There are multiple variations of this, but this is the basic variant.
        //ArrayAdapter<Object> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, myList);
        //set the spinners adapter to the previously created one.
        //dropdown.setAdapter(adapter);
        FloatingActionButton b1 = (FloatingActionButton) findViewById(R.id.btnClock);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an instance of the dialog fragment and show it
//                RangeTimePickerDialog dialog = new RangeTimePickerDialog();
////                dialog.newInstance();
////                dialog.setRadiusDialog(20); // Set radius of dialog (default is 50)
////                dialog.setIs24HourView(true); // Indicates if the format should be 24 hours
////                dialog.setColorBackgroundHeader(R.color.colorPrimary); // Set Color of Background header dialog
////                dialog.setColorTextButton(R.color.colorPrimaryDark); // Set Text color of button
////                FragmentManager fragmentManager = getFragmentManager();
////                dialog.show(fragmentManager, "");

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddPersonalTimeActivity.this,android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        //todo on time set
                        //time.setText(selectedHour + ":" + selectedMinute);
                        timePicker.setIs24HourView(true);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

    }

    public void onClick1(View v) {
        //todo save the updated alert days
        boolean[] days = new boolean[7];
        for (int i = 0; i < days.length; i++)
            days[i] = daysArr[i].isChecked();
        int listCount = dropdown.getSelectedItemPosition();
        if (listCount > 0) {
            AlertsSaver alert = new AlertsSaver(this, Integer.toString(keyList.get(listCount - 1)));
            alert.setDays(this, days);
            Toast.makeText(this, "YOUR DAYS HAVE BEEN SAVED", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "PLEASE SELECT AN ITEM", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSelectedTime(int hourStart, int minuteStart, int hourEnd, int minuteEnd) {
        //todo save the updated alert hours
        int listCount = dropdown.getSelectedItemPosition();
        if (listCount > 0) {
            AlertsSaver alert = new AlertsSaver(this, Integer.toString(keyList.get(listCount - 1)));
            String[] arr = new String[2];
            arr[0] = Integer.toString(hourStart) + ":" +
                    Integer.toString(minuteStart);
            arr[1] = Integer.toString(hourEnd) + ":" +
                    Integer.toString(minuteEnd);
            alert.setHours(this, arr);
            Toast.makeText(this, alert.toString(), Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "PLEASE SELECT AN ITEM", Toast.LENGTH_SHORT).show();

    }

    public void moveToHome(View v) {
        //todo moves to HomePage class
        Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
        startActivity(intent);
    }

    public void setAlarm(){
        scheduleNotification(getNotification("10 second delay"), 10000);
    }

    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, AlertPublisher.class);
        notificationIntent.putExtra(AlertPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(AlertPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }
    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID);
        }
        return builder.build();
    }
}
