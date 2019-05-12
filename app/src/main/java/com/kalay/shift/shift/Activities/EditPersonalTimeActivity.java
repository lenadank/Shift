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
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kalay.shift.shift.Classes.Alert;
import com.kalay.shift.shift.Classes.AlertPublisher;
import com.kalay.shift.shift.Classes.AlertsSaver;
import com.kalay.shift.shift.Classes.SharedPreferencesManager;
import com.kalay.shift.shift.R;
import com.mcsoft.timerangepickerdialog.RangeTimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.kalay.shift.shift.Activities.MainActivity.CHANNEL_ID;

/**
 * Created by romdolinger on 4/14/18.
 */

public class EditPersonalTimeActivity extends Activity implements RangeTimePickerDialog.ISelectedTime {

    EditText alertTitle;
    EditText alertContent;
    CheckBox[] daysArr;
    Button saveAlert;

    int hour;
    int minute;

    String interest;
    int index;

    SharedPreferencesManager manager = SharedPreferencesManager.getInstance();
    //Interests interests = (Interests) manager.getStoredData(this, "Interests", Interests.class);

    int i = AlertsSaver.startKey;
    static final String names[] = {"ראשון", "שני", "שלישי", "רביעי", "חמישי", "שישי", "שבת"};
    Spinner dropdown;
    List<Integer> keyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        SharedPreferencesManager manager = SharedPreferencesManager.getInstance();
//        interests = (Interests) manager.getStoredData(this, "Interests", Interests.class);

        Bundle bundle = getIntent().getExtras();
        interest = bundle.getString("interest");
        index = bundle.getInt("position");





        //alertTitle = new EditText(getApplicationContext());
        //alertContent = new EditText(getApplicationContext());
        daysArr = new CheckBox[7];
        //saveAlert = new Button(getApplicationContext());
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

        Button b1 = (Button) findViewById(R.id.btnClock);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Calendar mcurrentTime = Calendar.getInstance();
//                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
//                int minute = mcurrentTime.get(Calendar.MINUTE);
                minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(EditPersonalTimeActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker,
                                          int selectedHour,
                                          int selectedMinute) {
                        //todo on time set
                        System.out.println(selectedHour + "," + selectedMinute);
                        //time.setText(selectedHour + ":" + selectedMinute);
                        timePicker.setIs24HourView(true);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

    }

    public void onClick(View v) {



        boolean[] days = new boolean[7];
        for (int i = 0; i < days.length; i++)
            days[i] = daysArr[i].isChecked();
        //int listCount = dropdown.getSelectedItemPosition();
        //if (listCount > 0) {
            String nextKey = SharedPreferencesManager.getInstance().nextEmpty(this);
//            AlertsSaver alert = new AlertsSaver(this, nextKey);
        Alert alert = new Alert(alertContent.getText().toString(),days,null, alertTitle.getText().toString() );
//            alert.setDays(this, days);

        //interests.addNotification(interest, alert);

            Toast.makeText(this, "YOUR DAYS HAVE BEEN SAVED",
                    Toast.LENGTH_SHORT).show();
       // } else
            //Toast.makeText(this, "PLEASE SELECT AN ITEM", Toast.LENGTH_SHORT).show();
        //interests.save(this);

    }

    @Override
    public void onSelectedTime(int hourStart, int minuteStart, int hourEnd, int minuteEnd) {
        //todo save the updated alert hours
        int listCount = dropdown.getSelectedItemPosition();
        if (listCount > 0) {
            AlertsSaver alert = new AlertsSaver(this,
                    Integer.toString(keyList.get(listCount - 1)));
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

    public void setAlarm(String content, int hours, int minutes){
        minutes = minutes + hours * 60;
        long milliseconds = minutes * 60 * 1000;
        scheduleNotification(getNotification(content),milliseconds);
    }

    private void scheduleNotification(Notification notification, long time) {

        Intent notificationIntent = new Intent(this, AlertPublisher.class);
        notificationIntent.putExtra(AlertPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(AlertPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = time;
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
