package com.kalay.shift.shift.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kalay.shift.shift.Classes.LocalService;
import com.kalay.shift.shift.Classes.SharedPreferencesManager;
import com.kalay.shift.shift.Classes.User;
import com.kalay.shift.shift.R;

public class MainActivity extends Activity {
    public static Activity currActivity;     // todo: warning on memory leakage.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SharedPreferencesManager manager = SharedPreferencesManager.getInstance();
//        User u = (User) manager.getStoredData(MainActivity.this, "User", User.class);
//        if(u != null) {
//            Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
//            startActivity(intent);
//        }


        setContentView(R.layout.activity_main);
        currActivity = this;
        startService(new Intent(this, LocalService.class));
    }

    public void onPressButton(View v) {
        Log.d("onStart", "Hello from on start");
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        sendBroadcast(intent);
        startActivity(intent);
    }
    public void knownUserState()
    {
        SharedPreferencesManager manager = SharedPreferencesManager.getInstance();
        User user = (User) manager.getStoredData(MainActivity.this,"User", User.class);
        if(user == null)
        {
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
        }
        else if (user.getGender()== null) {
            Intent myIntent = new Intent(this, SignUpActivity.class);
            startActivity(myIntent);
        }
        else  if(user.getName()== null)    {
            Intent myIntent = new Intent(this, SignUpActivity.class);
            startActivity(myIntent);
           }
         ///else  if(user.getInterestsActivity == null){
      //  Intent myIntent = new Intent(this, InterestsActivity.class);
      //  startActivity(myIntent);
           //}
        // todo: InterestsActivity - block user advance in case nothing was selected.
    }
}
