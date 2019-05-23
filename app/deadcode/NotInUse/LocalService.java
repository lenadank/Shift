//package com.kalay.shift.shift.Classes;
//import android.app.Activity;
//import android.app.AlarmManager;
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.os.Binder;
//import android.os.IBinder;
//import android.util.Log;
//
//import com.kalay.shift.shift.Activities.MainActivity;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Random;
//
//public class LocalService extends Service {
//
//    private NotificationManager mNM;
//    private int NOTIFICATION = 0;
//
//    AlarmManager alarmManager;
//    PendingIntent pintent;
//    int i = AlertsSaver.startKey;
//
//    public class LocalBinder extends Binder {
//        LocalService getService() {
//            return LocalService.this;
//        }
//    }
//
//    @Override
//    public void onCreate() {
//        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        pintent = PendingIntent.getBroadcast(this, 0, new Intent("com.blah.blah.somemessage"), 0);
//
//        BroadcastReceiver receiver = new BroadcastReceiver() {
//
//            @Override
//            public void onReceive(Context context, Intent _) {
//                //context.unregisterReceiver( this ); // this == BroadcastReceiver, not Activity
//                Log.v("testAlarm", "got here!");
//
//                Activity activity = MainActivity.currActivity;
//                List<String> deleted = AlertsSaver.returnDeltedPlaces(activity);
//                AlertsSaver alert;
//                List<AlertsSaver> myList = new ArrayList<>();
//                while (true) {
//                    try {
//                        if (deleted.size() == 0 || (deleted.size() != 0 && !deleted.contains(getString(i)))) {
//                            alert = new AlertsSaver(activity, Integer.toString(i));
//                            myList.add(alert);
//                        }
//                        i++;
//                    }
//                    catch (Exception e) {
//                        break;
//                    }
//                }
//                /* */
//                for (int i = 0; i < myList.size(); i++) {
//                    Calendar now = Calendar.getInstance();
//                    if (myList.get(i).getAlertDays()[now.get(Calendar.DAY_OF_WEEK)]) {
//                        CharSequence text = myList.get(i).getAlertText();
//                        CharSequence title = "התראת Shift";
//                        String minHour = myList.get(i).getAlertHours()[0];
//                        String maxHour = myList.get(i).getAlertHours()[1];
//                        Calendar time = GetNotificationTime(Integer.parseInt(maxHour.substring(0, 1)), Integer.parseInt(minHour.substring(0, 1)));
//                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//                        PendingIntent contentIntent = null;
//                        Notification notification = new Notification.Builder(context)
//                                //.setSmallIcon(R.drawable.notification_logo)
//                                .setTicker(text)
//                                .setWhen(time.getTimeInMillis())
//                                .setContentTitle(title)
//                                .setContentText(text)
//                                .setContentIntent(contentIntent)
//                                .setSound(soundUri)
//                                .setPriority(Notification.PRIORITY_MAX)
//                                .build();
//                        mNM.notify(NOTIFICATION, notification);
//                    }
//                }
//                /* */
//                Log.v("testAlarm","got here!");
//
//                CharSequence text = "נראה לנו שזה הזמן המתאים!";
//                CharSequence title = "מתי בפעם האחרונה יצאת לטיול?";
//                Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//                PendingIntent contentIntent = null;
//                Notification notification = new Notification.Builder(context)
//                        //.setSmallIcon(R.drawable.notification_logo)
//                        .setTicker(text)
//                        .setWhen(System.currentTimeMillis())
//                        .setContentTitle(title)
//                        .setContentText(text)
//                        .setContentIntent(contentIntent)
//                        .setSound(soundUri)
//                        .setPriority(Notification.PRIORITY_MAX)
//                        .build();
//                /* */
//                mNM.notify(NOTIFICATION, notification);
//
//            }
//        };
//        this.registerReceiver(receiver, new IntentFilter("com.blah.blah.somemessage"));
//        TriggerTomorrowAlarmManager();
//
//    }
//
//
//    private void TriggerTomorrowAlarmManager() {
//        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        Calendar tomorrow = GetNextTime();
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, tomorrow.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pintent);
//    }
//
//
//    @Override
//    public void onDestroy() {
//        Log.i("LocalService", "onDestroy");
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.i("LocalService", "Received start id " + startId + ": " + intent);
//        PendingIntent contentIntent = null;
//        Notification notification = new Notification.Builder(this)
//                .setTicker("Shift פועל")
//                //.setSmallIcon(R.drawable.notification_permanent_logo)
//                .setWhen(System.currentTimeMillis())
//                .setContentTitle("Shift פועל")
//                .setContentText("ישומון ההתראות פעיל כעת")
//                .setContentIntent(contentIntent)
//                .setPriority(Notification.PRIORITY_MAX)
//                .build();
//        startForeground(10, notification);
//
//        return START_STICKY;
//    }
//
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return mBinder;
//    }
//
//    private final IBinder mBinder = new LocalBinder();
//
//    public Calendar GetNotificationTime(int MAXHOUR, int MINHOUR) {
//        Calendar today = Calendar.getInstance();
//
//        Random rnd = new Random();
//        int hour;
//
//        hour = (rnd.nextInt(MAXHOUR - MINHOUR) + 1 + MINHOUR);
//        today.set(Calendar.HOUR_OF_DAY, hour);
//        today.set(Calendar.MINUTE, 0);
//        today.set(Calendar.SECOND, 0);
//        // add one day to the date/calendar
//        today.add(Calendar.DAY_OF_YEAR, 1);
//        Log.i("LocalService", today.toString());
//
//        return today;
//
//    }
//    public Calendar GetNextTime() {
//        Calendar nextDay = Calendar.getInstance();
//        nextDay.set(Calendar.HOUR, 0);
//        nextDay.set(Calendar.MINUTE, 0);
//        nextDay.set(Calendar.SECOND, 0);
//        // add one day to the date/calendar
//        nextDay.add(Calendar.DAY_OF_YEAR, 1);
//        Log.i("LocalService", nextDay.toString());
//        return nextDay;
//
//    }
//
//
//}