package com.kalay.shift.shift;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by romdolinger on 4/14/18.
 */

public class SetAlert extends AppCompatActivity {

    SharedPreferencesManager sharedPreferencesManager;
    Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alert);
        Button blogin = (Button)findViewById(R.id.button2);
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo adds and saves the new alert
                EditText editText1 = (EditText) v.findViewById(R.id.editText);
                EditText editText2 = (EditText) v.findViewById(R.id.editText2);
                String text1, text2;
                try {
                    text1 = editText1.getText().toString();
                    text2 = editText2.getText().toString();
                    AlertsSaver alert = new AlertsSaver(activity, text1, AlertsSaver.hours, AlertsSaver.days, text2);
                    Toast.makeText(activity, "ALERT " + text2 + " SAVED", Toast.LENGTH_SHORT).show();
                }
                catch (NullPointerException e) {
                    Toast.makeText(activity, "OK", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
    public void moveToTime(View v) {
        //todo moves to AddPersonalTime class
        Intent intent = new Intent(getApplicationContext(), AddPersonalTime.class);
        startActivity(intent);
    }

}



