package com.kalay.shift.shift.Classes;

import android.app.Activity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Interests {
    //private List<String> interests = new ArrayList<>();
    private Map<String, List<Alert>> notifications = new LinkedHashMap<>();

    public Interests(){
        notifications.put("***", new ArrayList<Alert>());
        List<Alert> value = notifications.get("***");
        String[] hours = {"00","00"};
        value.add(new Alert("You need to run",new boolean[7],hours,"Run!"));
    }



    public List<String> getInterests() {
        return new ArrayList<>(notifications.keySet());
    }

    public List<Alert> getNotifications(String interest) {
        return notifications.get(interest);
    }

    public void addInterest(String interest) {
        if (!notifications.containsKey(interest))
            notifications.put(interest, new ArrayList<Alert>());
    }

    public void addNotification(String interest, Alert notification) {
        List<Alert> value = notifications.get(interest);
        if (value == null) {
            value = new ArrayList<Alert>();
            notifications.put(interest, value);
        }

        boolean idenFound = false;

        for (Alert alert : value){

            if (alert.getAlertTitle() == notification.getAlertTitle()){
                value.remove(alert);
                value.add(notification);
                idenFound = true;
            }

        }

        if (!idenFound){
            value.add(notification);

        }

//        value.add(notification);
    }

    public void removeInterest(String interest) {

        if (notifications.containsKey(interest))
            notifications.remove(interest);
    }

    public void removeNotification(String interest, String notification){
        List<Alert> value = notifications.get(interest);
        for (Alert alert : value){

            if (alert.getAlertTitle().equals(notification)){
                value.remove(alert);
            }

        }


    }
    public void save(Activity activity){
        SharedPreferencesManager manager = SharedPreferencesManager.getInstance();
        manager.storeData(activity,"Interests",this);
    }
}
