package com.kalay.shift.shift.Activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kalay.shift.shift.Classes.LocalService;
import com.kalay.shift.shift.Classes.MyNotificationPublisher;
import com.kalay.shift.shift.Classes.SharedPreferencesManager;
import com.kalay.shift.shift.Classes.User;
import com.kalay.shift.shift.R;

import java.util.Date;

public class MainActivity extends Activity {
    public static Activity currActivity;

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

        scheduleNotification(getApplicationContext(),0,123);
    }

    public void onPressButton(View v) {
        Log.d("onStart", "Hello from on start");
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        sendBroadcast(intent);
        startActivity(intent);
    }

    public void scheduleNotification(Context context, long delay, int notificationId) {//delay is after how much time(in millis) from current time you want to schedule the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("testest")
                .setContentText("checkchekc")
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent activity = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(activity);

        Notification notification = builder.build();

        Intent notificationIntent = new Intent(context, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, notificationId);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }
}
