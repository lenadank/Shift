package com.kalay.shift.shift;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romdolinger on 5/4/18.
 */

public class AlertsSaver {

    public static List<String> deleted = new ArrayList<>();
    private Alert alert;
    private String key;
    public static final String [] hours = {"07:00", "19:00"};
    public static final boolean [] days = {true, true, true, true, true, false, false};
    public static final int startKey = 1000;
    private static SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();

    public AlertsSaver(Activity activity, String alert, String[] hours_arr, boolean [] days_arr, String alertTitle) {
        if (alert != null && hours_arr.length == 2 && days_arr != null) {
            this.alert = new Alert(alert, days_arr, hours_arr, alertTitle);
            this.key = this.sharedPreferencesManager.nextEmpty(activity);
            if (deleted.contains(this.key))
                this.updateDeleted();
            this.sharedPreferencesManager.storeData(activity, this.key, this.alert.toString());
        }

    }

    private void updateDeleted() {
        boolean changed = false;
        int place = 0;
        for (String obj: deleted) {
            if (changed == false && obj.toString().equals(this.key)) {
                deleted.set(place, null);
                changed = true;
            }
            else if (changed == true)
                deleted.set(place - 1, obj);
            place++;
        }
    }

    public AlertsSaver(Activity activity, String key) {
            String ds = (String) sharedPreferencesManager.getStoredData(activity, key);
            String alertTitle = ds.substring(0, ds.indexOf(","));
            ds = ds.replace(alertTitle, "");
            ds = ds.substring(1);
            String alert = ds.substring(0, ds.indexOf(","));
            ds = ds.replace(alert, "");
            ds = ds.substring(2);
            this.key = key;
            String hour = ds.substring(0, ds.indexOf("],"));
            ds.replace(hour, "");
            hour = hour.replace("[", "");
            hour = hour.replace("]", "");
            ds = ds.replaceAll(" ", "");
            boolean[] hours = toBooleanArray(hour);
            ds = ds.replaceAll(ds.substring(0, ds.indexOf("],")), "");
            ds = ds.replace("[", "");
            ds = ds.replace("]", "");
            ds = ds.substring(1);
            this.alert = new Alert(alertTitle, hours, ds.split(","), alert);
            if (deleted.isEmpty())
                return;
            else
                if (deleted.contains(this.key))
                    this.updateDeleted();

        }



    private static boolean[] toBooleanArray(String str) {
        String[] parts = str.split(",");
        boolean[] array = new boolean[parts.length];
        for (int i = 0; i < parts.length; i++)
            array[i] = Boolean.parseBoolean(parts[i]);
        return array;
    }
    public void setInfo(Activity activity, String userInfo) {
        this.alert.setText(userInfo);
        sharedPreferencesManager.storeData(activity, this.key, this.alert);

    }

    public void setHours(Activity activity, String [] userInfo) {
        this.alert.setHours(userInfo);
        sharedPreferencesManager.storeData(activity, this.key, this.alert);

    }

    public void setAlertTitle(Activity activity, String [] userInfo, String alertTitle) {
        this.alert.setHours(userInfo);
        this.alert.setAlertTitle(alertTitle);
        sharedPreferencesManager.storeData(activity, this.key, this.alert);

    }

    public void setDays(Activity activity, boolean [] userInfo) {
        if (userInfo.length == 7) {
            this.alert.setDays(userInfo);
            sharedPreferencesManager.storeData(activity, this.key, this.alert);
        }
        else
            throw new RuntimeException("userInfo length must be 7. Length: " + userInfo.length);

    }

    public Alert getAlert() {
        return this.alert;
    }

    public String getAlertTitle() {
        return this.alert.getAlertTitle();
    }

    public String getAlertText() {
        return this.alert.getText();
    }

    public boolean [] getAlertDays() {
        return this.alert.getDays();
    }


    public String[] getAlertHours() {
        return this.alert.getHours();
    }

    public static void deleteAlert(Activity activity, String key) {
        sharedPreferencesManager.deleteAlert(activity, key);
    }

    public static List<String> returnDeltedPlaces(Activity activity) {
        List<String> toReturn = new ArrayList<>();
        for (String obj : deleted)
            toReturn.add(obj);
        return toReturn;
    }

    public static String findByContent(Activity activity, String alertInfo) {
        return sharedPreferencesManager.findByInfo(activity, alertInfo);
    }

    public static String nextEmpty(Activity activity) {
        return sharedPreferencesManager.nextEmpty(activity);
    }

    @Override
    public String toString() {
        return "AlertsSaver{" +
                "alert=" + alert +
                ", key='" + key + '\'' +
                '}';
    }
}

