package com.kalay.shift.shift;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kalay.shift.shift.interests.InterestCultureActivity;
import com.kalay.shift.shift.interests.InterestFamilyActivity;
import com.kalay.shift.shift.interests.InterestSportsActivity;
import com.kalay.shift.shift.interests.InterestTripsActivity;

/**
 * Created by gal zohar on 3/25/2018.
 */

public class InterestsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
    }

    public void trips_interest(View v) {
        Intent intent = new Intent(getApplicationContext(), InterestTripsActivity.class);
        startActivity(intent);
    }
    public void culture_interest(View v) {
        Intent intent = new Intent(getApplicationContext(), InterestCultureActivity.class);
        startActivity(intent);
    }
    public void sport_interest(View v) {
        Intent intent = new Intent(getApplicationContext(), InterestSportsActivity.class);
        startActivity(intent);
    }
    public void family_interest(View v) {
        Intent intent = new Intent(getApplicationContext(), InterestFamilyActivity.class);
        startActivity(intent);
    }

    public void onNextInterests(View v) {
        Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
        startActivity(intent);
    }

    public void moveBack (View v) {
        //todo moves to PersonalInfo class
        Toast.makeText(getApplicationContext(),"Not Working !!!", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(getApplicationContext(), PersonalInfo.class);
        //startActivity(intent);
    }

}
