package com.kalay.shift.shift.Classes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Interests {
    //private List<String> interests = new ArrayList<>();
    private Map<String, List<Alert>> notifications = new LinkedHashMap<>();

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
        value.add(notification);
    }

    public void removeInterest(String interest) {

        if (notifications.containsKey(interest))
            notifications.remove(interest);
    }

    public void removeNotification(String interest, Alert notification){
        List<Alert> value = notifications.get(interest);
        if (value.contains(notification)) {
            value.remove(notification);
        }

    }










}
