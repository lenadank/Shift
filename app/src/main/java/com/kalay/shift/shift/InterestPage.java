package com.kalay.shift.shift;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kalay.shift.shift.interests.InterestCultureActivity;
import com.kalay.shift.shift.interests.InterestFamilyActivity;
import com.kalay.shift.shift.interests.InterestSportsActivity;
import com.kalay.shift.shift.interests.InterestTripsActivity;


public class InterestPage extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
    }
    public void first_interst(View v){
        Intent intent = new Intent(getApplicationContext(), InterestTripsActivity.class);
        startActivity(intent);
    }
    public void second_interst(View x){
        Intent intent = new Intent(getApplicationContext(), InterestCultureActivity.class);
        startActivity(intent);
    }
    public void  third_interst(View z){
        Intent intent = new Intent(getApplicationContext(), InterestSportsActivity.class);
        startActivity(intent);
    }
    public void fourth_interst(View y){
        Intent intent = new Intent(getApplicationContext(), InterestFamilyActivity.class);
        startActivity(intent);
    }
}