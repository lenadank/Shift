package com.kalay.shift.shift.Activities;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kalay.shift.shift.Classes.Alert;
import com.kalay.shift.shift.Classes.AlertManager;
import com.kalay.shift.shift.Classes.AlertsSaver;
import com.kalay.shift.shift.Classes.Interests;
import com.kalay.shift.shift.Classes.SharedPreferencesManager;
import com.kalay.shift.shift.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.kalay.shift.shift.Classes.AlertsSaver.days;

/**
 * Created by romdolinger on 4/14/18.
 */

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

    int i = AlertsSaver.startKey;
    static final String names[] = {"ראשון", "שני", "שלישי", "רביעי", "חמישי", "שישי", "שבת"};
    Spinner dropdown;
    List<Integer> keyList = new ArrayList<>();

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
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            interest = bundle.getString("interest");
            i = bundle.getInt("position");
            alertTitle.setText(interests.getNotifications(interest).get(i).getAlertTitle());
            alertContent.setText(interests.getNotifications(interest).get(i).getText());
            daysArr[0].setChecked(interests.getNotifications(interest).get(i).getDays()[0]);
            daysArr[1].setChecked(interests.getNotifications(interest).get(i).getDays()[1]);
            daysArr[2].setChecked(interests.getNotifications(interest).get(i).getDays()[2]);
            daysArr[3].setChecked(interests.getNotifications(interest).get(i).getDays()[3]);
            daysArr[4].setChecked(interests.getNotifications(interest).get(i).getDays()[4]);
            daysArr[5].setChecked(interests.getNotifications(interest).get(i).getDays()[5]);
            daysArr[6].setChecked(interests.getNotifications(interest).get(i).getDays()[6]);

        }
        final String oldAlertTitle = alertTitle.getText().toString();
                b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
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
                boolean[] activeDays = new boolean[7];
                for (int i = 0; i < daysArr.length; i++) {
                    if (daysArr[i].isChecked()) {
                        activeDays[i] = true;
                        int requestCode = 0;
                        AlertManager.setAlarm(getApplicationContext(), alertTitle.getText().toString(), alertContent.getText().toString(), i + 1, myHour, myMinute, requestCode);
                        System.out.println(requestCode);
                        //todo save the "requestCode" variable with the alert (in shared preferences) to be able to delete the Alert if needed.
                        //todo to delete alert use AlertManager.deleteAlarm
                    }
                }


                Alert alert = new Alert(alertContent.getText().toString(), activeDays, null, alertTitle.getText().toString());

//                if (index >= 0)
//                    interests.getNotifications(interest).set(index, alert);
//                else
//                    interests.addNotification(interest, alert);
                interests.removeNotification("***", oldAlertTitle );
                interests.addNotification("***",alert);
                interests.save(EditPersonalTimeActivity.this);
                Intent intent = new Intent(getApplicationContext(), InterestsHandlerActivity.class);
                startActivity(intent);
            }
        });
    }
}
