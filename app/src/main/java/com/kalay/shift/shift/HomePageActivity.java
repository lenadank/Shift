package com.kalay.shift.shift;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by gal zohar on 4/6/2018.
 */

public class HomePageActivity extends Activity {
    public TextView nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        nameText = findViewById(R.id.textView);
    }

    public void changeTimes(View x) {
        Intent myIntent = new Intent(this, AddPersonalTime.class);
        startActivity(myIntent);
    }

    public void changeInterests(View z) {
        Intent myIntent = new Intent(this, prefrencesChangesActivity.class);
        startActivity(myIntent);
    }

    public void addNotifications(View y) {
        Intent myIntent = new Intent(this, SetAlert.class);
        startActivity(myIntent);
    }

}
