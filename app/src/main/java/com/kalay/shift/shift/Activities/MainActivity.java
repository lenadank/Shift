package com.kalay.shift.shift.Activities;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.kalay.shift.shift.Classes.Alert;
import com.kalay.shift.shift.Classes.Interests;
import com.kalay.shift.shift.Classes.SharedPreferencesManager;
import com.kalay.shift.shift.Classes.User;
import com.kalay.shift.shift.R;

public class MainActivity extends Activity {
    //public static Activity currActivity;     // todo: warning on memory leakage.

    public static final String CHANNEL_ID = "channel1";
    private String channel_name = "myChannel";
    private String channel_description = "newChannel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

//        SharedPreferencesManager manager = SharedPreferencesManager.getInstance();
//        Interests interests = (Interests) manager.getStoredData(MainActivity.this, "Interests", Interests.class);
//        if (interests == null) {
//            interests = new Interests();
//        }
//        String[] hours = {"00","00"};
//        interests.addNotification("***",new Alert("You need to run",new boolean[7],hours,"Run!"));
//        interests.save(this);

        //this ^^^ is to enter starting alert or else code go boom.


//        Shar//        User u = (User) manager.getStoredData(MainActivity.this, "User", User.class);edPreferencesManager manager = SharedPreferencesManager.getInstance();
//        if(u != null) {
//            Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
//            startActivity(intent);
//        }
//        currActivity = this;
//        startService(new Intent(this, LocalService.class));
    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = channel_name;
            String description = channel_description;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void onGetStartButtonPress(View v) {
        knownUserState();
//        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
//        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
//        sendBroadcast(intent);
//        startActivity(intent);
    }

    public void knownUserState() {
        SharedPreferencesManager manager = SharedPreferencesManager.getInstance();
        User user = (User) manager.getStoredData(MainActivity.this, "User", User.class);
        if (user == null || user.getName() == null || user.getGender() == null) {
            Intent myIntent = new Intent(this, SignUpActivity.class);
            startActivity(myIntent);
        } else {
            Intent myIntent = new Intent(this, InterestsHandlerActivity.class);
            startActivity(myIntent);
        }
    }
}
