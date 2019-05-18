package com.kalay.shift.shift.Classes;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.kalay.shift.shift.R;

import java.util.Calendar;

import static com.kalay.shift.shift.Activities.MainActivity.CHANNEL_ID;

/*
    This class will handle the alert save in the device to be published
 */
public abstract class AlertManager {

    public static void setAlarm(Context context, String title, String content, int day, int hour, int minute, int requestCode) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.set(Calendar.DAY_OF_WEEK, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.getTimeInMillis(); //to commit changes in calendar. DO NOT DELETE IT

        if (System.currentTimeMillis() > cal.getTimeInMillis()) {
            cal.roll(Calendar.DAY_OF_MONTH, 7);
        }

        scheduleNotification(context, getNotification(context, title, content), cal.getTimeInMillis(), requestCode);
    }

    private static void scheduleNotification(Context context, Notification notification, long time, int requestCode) {
        Intent notificationIntent = new Intent(context, AlertPublisher.class);
        notificationIntent.putExtra(AlertPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(AlertPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY * 7, pendingIntent);
    }

    private static Notification getNotification(Context context, String title, String content) {
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setColorized(true);
        builder.setSmallIcon(R.drawable.shifticon);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID);
        }
        return builder.build();
    }

    public static void deleteAlarm(Context context, int reqCode){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent(context, AlertPublisher.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,reqCode, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }

}
