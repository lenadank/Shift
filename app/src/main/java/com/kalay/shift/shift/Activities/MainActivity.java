package com.kalay.shift.shift.Activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import com.kalay.shift.shift.Classes.AlertPublisher;
import com.kalay.shift.shift.Classes.LocalService;
import com.kalay.shift.shift.Classes.SharedPreferencesManager;
import com.kalay.shift.shift.Classes.User;
import com.kalay.shift.shift.R;

public class MainActivity extends Activity {
    private static final String CHANNEL_ID = "channel1";

    public static Activity currActivity;     // todo: warning on memory leakage.

    private String channel_name = "myChannel";
    private String channel_description = "newChannel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SharedPreferencesManager manager = SharedPreferencesManager.getInstance();
//        User u = (User) manager.getStoredData(MainActivity.this, "User", User.class);
//        if(u != null) {
//            Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
//            startActivity(intent);
//        }
        createNotificationChannel();
        // Days, Hours, Title, Content,
        setAlarm();
        setContentView(R.layout.activity_main);
        currActivity = this;
        startService(new Intent(this, LocalService.class));
    }

    public void setAlarm(){
        scheduleNotification(getNotification("10 second delay"), 10000);
    }

    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, AlertPublisher.class);
        notificationIntent.putExtra(AlertPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(AlertPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
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

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID);
        }
        return builder.build();
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
