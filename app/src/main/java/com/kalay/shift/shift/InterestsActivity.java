package com.kalay.shift.shift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kalay.shift.shift.interests.InterestCultureActivity;
import com.kalay.shift.shift.interests.InterestFamilyActivity;
import com.kalay.shift.shift.interests.InterestSportsActivity;
import com.kalay.shift.shift.interests.InterestTripsActivity;

/**
 * Created by gal zohar on 3/25/2018.
 */

public class InterestsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
    }

    public void first_interest(View v) {
        Intent intent = new Intent(getApplicationContext(), InterestTripsActivity.class);
        startActivity(intent);
    }
    public void second_interest(View v) {
        Log.d("onNext","hello from on next");
        Intent intent = new Intent(getApplicationContext(), InterestCultureActivity.class);
        startActivity(intent);
    }
    public void third_interest(View v) {
        Log.d("onNext","hello from on next");
        Intent intent = new Intent(getApplicationContext(), InterestSportsActivity.class);
        startActivity(intent);
    }
    public void fourth_interest(View v) {
        Log.d("onNext","hello from on next");
        Intent intent = new Intent(getApplicationContext(), InterestFamilyActivity.class);
        startActivity(intent);
    }

    public void onNextInterests(View v) {
        Log.d("onNext","hello from on next");
        Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
        startActivity(intent);
    }

    public void moveBack (View v) {
        //todo moves to PersonalInfo class
        Intent intent = new Intent(getApplicationContext(), PersonalInfo.class);
        startActivity(intent);
    }

}
