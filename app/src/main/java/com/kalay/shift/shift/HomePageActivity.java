package com.kalay.shift.shift;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by gal zohar on 4/6/2018.
 */

public class HomePageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }
    public void changeTimes(View x){
        Intent myIntent = new Intent(this, AddPersonalTime.class);
        startActivity(myIntent);
    }
    public void  changeInterests(View z){
        Intent myIntent = new Intent(this, prefrencesChangesActivity.class);
        startActivity(myIntent);
    }
    public void addNotifications(View y){
        Intent myIntent = new Intent(this, SetAlert.class);
        startActivity(myIntent);
    }

}
