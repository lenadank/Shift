package com.kalay.shift.shift.Classes;

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
    public static final boolean [] days = {true, true, true, true, true, true, true};
    public static final int startKey = 1000;
    private static SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();

    public AlertsSaver(Activity activity, String alert, String[] hours_arr,
                       boolean [] days_arr, String alertTitle) {
        if (alert != null && !alert.equals("") && hours_arr.length == 2 &&
                days_arr != null) {
            this.alert = new Alert(alert, days_arr, hours_arr, alertTitle);
            this.key = this.sharedPreferencesManager.nextEmpty(activity);
            if (deleted.contains(this.key))
                this.updateDeleted();
            this.sharedPreferencesManager.storeData(activity, this.key, this.alert);
        }

    }
    // not critical but could be written better
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
        this.alert = (Alert) sharedPreferencesManager.getStoredData(activity, key, Alert.class);
        this.key = key;
        if (deleted.isEmpty())
            return;
        else
            if (deleted.contains(this.key))
                this.updateDeleted();

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

