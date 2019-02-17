package com.kalay.shift.shift.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.kalay.shift.shift.Classes.FieldsOfInterest;
import com.kalay.shift.shift.R;

import java.util.ArrayList;

public class prefrencesChangesActivity extends Activity {
    ArrayList<CheckBox> check1 = new ArrayList<CheckBox>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefrences_changes);
        //read input array
        String names[] = {"ספורט", "טיולים", "משפחה"};

        for (int i = 0; i < names.length; i++) {
            //create the UI check box
            final LinearLayout ll = findViewById(R.id.linearLayoutId);
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(names[i]);
            ll.addView(cb);
            check1.add(cb);

        }
    }
    public void saveChanges(View v) {
        //todo save the updated alert
        ArrayList<String> check_info = new ArrayList<String>();
        for (CheckBox c1: check1) {
            if (c1.isChecked())
                check_info.add(c1.getContext().toString());
        }
        new FieldsOfInterest(this, check_info);
    }
}
