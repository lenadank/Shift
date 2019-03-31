package com.kalay.shift.shift.Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Interests {
    //private List<String> interests = new ArrayList<>();
    private Map<String, List<Notification>> notifications = new LinkedHashMap<>();

    public List<String> getInterests() {
        return new ArrayList<>(notifications.keySet());
    }

    public List<Notification> getNotification(String interest) {
        return notifications.get(interest);
    }

    public void addInterest(String interest) {
        if (!notifications.containsKey(interest))
            notifications.put(interest, new ArrayList<Notification>());
    }

    public void addNotification(String interest, Notification notification) {
        List<Notification> value = notifications.get(interest);
        if (value == null) {
            value = new ArrayList<Notification>();
            notifications.put(interest, value);
        }
        value.add(notification);
    }
}
