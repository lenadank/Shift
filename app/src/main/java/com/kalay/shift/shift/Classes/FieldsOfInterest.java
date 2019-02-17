package com.kalay.shift.shift.Classes;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by romdolinger on 5/6/18.
 */

public class FieldsOfInterest {
    private static SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
    private List<String> info;
    private static final String fieldsKey = "4000";
    private static final List<String> allFields = new ArrayList<>(Arrays.asList("Sports", "Entertainment", "Culture", "Food", "Traveling", "Personal"));

    public static List<String> getAllFields() {
            return allFields;
    }

    public static void setAllFields(List<String> allFields) {
        allFields = allFields;
    }

    public FieldsOfInterest(Activity activity, List<String> fieldsOf) {
        this.info = new ArrayList<>();
        for (String obj : fieldsOf)
            this.info.add(obj);
        sharedPreferencesManager.storeData(activity, fieldsKey, this.info);
    }

    public List<String> getFields() {
        return this.info;
    }

    public void setFields(Activity activity, List<String> fieldsOf) {
        this.info.clear();
        for (String obj : fieldsOf)
            this.info.add(obj);
        sharedPreferencesManager.storeData(activity, fieldsKey, this.info);
    }

    public void addField(Activity activity, String fieldsOf) {
        this.info.add(fieldsOf);
        sharedPreferencesManager.storeData(activity, fieldsKey, this.info);
    }
}
