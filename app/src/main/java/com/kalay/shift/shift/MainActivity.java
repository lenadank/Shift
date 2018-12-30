package com.kalay.shift.shift;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    public static Activity currActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currActivity = this;
        startService(new Intent(this, LocalService.class));
    }

        public void onStart(View v) {
        Log.d("onStart","Hello from on start");
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        sendBroadcast(intent);
        startActivity(intent);
    }


}
