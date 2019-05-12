package com.kalay.shift.shift.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import com.kalay.shift.shift.Classes.SharedPreferencesManager;
import com.kalay.shift.shift.R;


/**
 * Created by romdolinger on 4/14/18.
 */

public class SetAlertActivity extends Activity {

    SharedPreferencesManager sharedPreferencesManager;
    Activity activity = this;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alert);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
//        Button blogin = (Button)findViewById(R.id.addDefault);
//        blogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //todo adds and saves the new alert
//                EditText editText1 = (EditText) findViewById(R.id.alertText);
//                EditText editText2 = (EditText) findViewById(R.id.alertTitle);
//                String text1, text2;
//                try {
//                    text1 = editText1.getText().toString();
//                    text2 = editText2.getText().toString();
//                    new AlertsSaver(activity, text1, AlertsSaver.hours, AlertsSaver.days, text2);
//                    Toast.makeText(activity, "ALERT " + text2 + " SAVED", Toast.LENGTH_SHORT).show();
//                }
//                catch (Exception e) {
//                    Toast.makeText(activity, "ERROR", Toast.LENGTH_SHORT).show();
//                }
//                FloatingActionButton b1 = (FloatingActionButton) findViewById(R.id.fab);
//                b1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Calendar mcurrentTime = Calendar.getInstance();
//                        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
//                        int minute = mcurrentTime.get(Calendar.MINUTE);
//                        TimePickerDialog mTimePicker;
//                        mTimePicker = new TimePickerDialog(SetAlertActivity.this, android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                                //todo on time set
//                                //time.setText(selectedHour + ":" + selectedMinute);
//                                timePicker.setIs24HourView(true);
//                            }
//                        }, hour, minute, true);//Yes 24 hour time
//                        mTimePicker.setTitle("Select Time");
//                        mTimePicker.show();
//                    }
//                }
//            }
//
//        });
    }
    public void moveToTime(View v) {
        //todo moves to AddPersonalTime class
        Intent intent = new Intent(getApplicationContext(), AddPersonalTimeActivity.class);
        startActivity(intent);
    }

}



