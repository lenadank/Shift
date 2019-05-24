package com.kalay.shift.shift.Activities;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.kalay.shift.shift.Classes.Alert;
import com.kalay.shift.shift.Classes.AlertManager;
import com.kalay.shift.shift.Classes.Interests;
import com.kalay.shift.shift.Classes.SharedPreferencesManager;
import com.kalay.shift.shift.R;

import java.util.Calendar;


/**
 * Created by romdolinger on 4/14/18.
 */

// fixme: back button duplicates interest list!

public class EditPersonalTimeActivity extends Activity {

    EditText alertTitle;
    EditText alertContent;
    CheckBox[] daysArr;
    Button saveAlert;

    public int myHour;
    public int myMinute;

    String interest;
    int index;

    SharedPreferencesManager manager;
    Interests interests;
    Alert alert;

//    static final String names[] = {"ראשון", "שני", "שלישי", "רביעי", "חמישי", "שישי", "שבת"};
//    Spinner dropdown;
//    List<Integer> keyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_time);

        manager = SharedPreferencesManager.getInstance();
        interests = (Interests) manager.getStoredData(EditPersonalTimeActivity.this, "Interests", Interests.class);

        //alertTitle = new EditText(getApplicationContext());
        //alertContent = new EditText(getApplicationContext());
        daysArr = new CheckBox[7];
        //saveAlert = new Button(getApplicationContext());

        alertTitle = findViewById(R.id.alertTitle);
        alertContent = findViewById(R.id.alertContent);
        daysArr[0] = findViewById(R.id.chbSunday);
        daysArr[1] = findViewById(R.id.chbMonday);
        daysArr[2] = findViewById(R.id.chbTuesday);
        daysArr[3] = findViewById(R.id.chbWednesday);
        daysArr[4] = findViewById(R.id.chbThursday);
        daysArr[5] = findViewById(R.id.chbFriday);
        daysArr[6] = findViewById(R.id.chbSaturday);

        Button b1 = findViewById(R.id.btnClock);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour;
                int minute;
                if (alert == null) {
                    hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    minute = mcurrentTime.get(Calendar.MINUTE);
                } else {
                    hour = myHour;
                    minute = myMinute;
                }
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(EditPersonalTimeActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker,
                                                  int selectedHour,
                                                  int selectedMinute) {
                                myHour = selectedHour;
                                myMinute = selectedMinute;
                                timePicker.setIs24HourView(true);
                            }
                        }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        Button save = findViewById(R.id.addAlert);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(EditPersonalTimeActivity.this, "Saving Alert", Toast.LENGTH_SHORT).show();

                boolean[] days = new boolean[daysArr.length];

                for (int i = 0; i < daysArr.length; i++) {
                    if (daysArr[i].isChecked()) {
                        days[i] = true;
                        int requestCode = 0;
                        AlertManager.setAlarm(getApplicationContext(), alertTitle.getText().toString(), alertContent.getText().toString(), i + 1, myHour, myMinute, requestCode);
                        System.out.println(requestCode);
                        //todo save the "requestCode" variable with the alert (in shared preferences) to be able to delete the Alert if needed.
                        //todo to delete alert use AlertManager.deleteAlarm
                    }
                }

                Alert alert = new Alert(alertContent.getText().toString(), days, new Pair<>(myHour, myMinute), alertTitle.getText().toString());

                if (index >= 0)
                    interests.getNotifications(interest).set(index, alert);
                else
                    interests.addNotification(interest, alert);

                interests.save(EditPersonalTimeActivity.this);
                Intent intent = new Intent(getApplicationContext(), InterestsHandlerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        interests = (Interests) manager.getStoredData(EditPersonalTimeActivity.this, "Interests", Interests.class);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            alert = null;
        } else {
            interest = bundle.getString("interest");
            index = bundle.getInt("position");
            if (index >= 0) {
                alert = interests.getNotifications(interest).get(index);
                alertTitle.setText(alert.getAlertTitle());
                alertContent.setText("Missing content in Alert class");
                boolean[] days = alert.getDays();
                for (int i = 0; i < daysArr.length; i++)
                    daysArr[i].setChecked(days[i]);
                Pair<Integer, Integer> time = alert.getHours();
                myHour = time.first;
                myMinute = time.second;
            }
        }
    }
}
