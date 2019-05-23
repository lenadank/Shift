package com.kalay.shift.shift.Classes;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by romdolinger on 5/6/18.
 */

public class Alert implements Serializable {
    private String text;
    private boolean[] days;
    private String hours;
    private String alertTitle;

    public Alert(String text, boolean[] days, String hours, String alertTitle) {
        this.text = text;
        this.days = days;
        this.hours = hours;
        this.alertTitle = alertTitle;
    }

    public String getAlertTitle() {
        return alertTitle;
    }

    public void setAlertTitle(String alertTitle) {
        this.alertTitle = alertTitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean[] getDays() {
        return days;
    }

    private final static String[] dayNames = {"א", "ב", "ג", "ד", "ה", "ו", "ש"};

    public String getDaysCompactRep() {
        String result ="";
        for (int i = 0; i < dayNames.length; i++) {
            System.out.println(days[i]);
            if (days[i])
                result += dayNames[i];
        }
        return result;
    }

    public void setDays(boolean[] days) {
        this.days = days;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return
                text + "," + this.alertTitle +
                        "," + Arrays.toString(days) +
                        "," + hours;


    }
}
