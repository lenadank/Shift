package com.kalay.shift.shift.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kalay.shift.shift.Classes.SharedPreferencesManager;
import com.kalay.shift.shift.Classes.User;
import com.kalay.shift.shift.R;

/**
 * Created by gal zohar on 4/6/2018.
 */

public class HomePageActivity extends Activity {
    public TextView nameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        nameText = findViewById(R.id.welcomeMsg);
        SharedPreferencesManager manager = SharedPreferencesManager.getInstance();
        User user = (User) manager.getStoredData(HomePageActivity.this,"User", User.class);
        nameText.setText(" שלום " + user.getName());
    }

    public void changeTimes(View view) {
        Intent myIntent = new Intent(this, AddPersonalTimeActivity.class);
        startActivity(myIntent);
    }

    public void changeInterests(View view) {
        Intent myIntent = new Intent(this, prefrencesChangesActivity.class);
        startActivity(myIntent);
    }

    public void addNotifications(View view) {
        Intent myIntent = new Intent(this, SetAlertActivity.class);
        startActivity(myIntent);
    }
    public void changeInfo(View view){
        Intent myIntent = new Intent(this,SignUpActivity.class);
        startActivity(myIntent);
    }

}
