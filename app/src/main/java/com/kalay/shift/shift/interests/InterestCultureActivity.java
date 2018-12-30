package com.kalay.shift.shift.interests;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.kalay.shift.shift.AlertsSaver;
import com.kalay.shift.shift.FieldsOfInterest;
import com.kalay.shift.shift.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 06/05/2018.
 */

public class InterestCultureActivity  extends AppCompatActivity {
    ArrayList<CheckBox> check1 = new ArrayList<CheckBox>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_culture);

        //read input array
        String names[] = {"אתרי אינטרנט", "סרטים", "הצגות"};
        for (int i = 0; i < names.length; i++) {
            //create the UI check box
            final LinearLayout ll = findViewById(R.id.linearLayoutId);
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(names[i]);
            ll.addView(cb);
            check1.add(cb);
        }
    }


    public void onClose(View v) {
        finish();
    }

    public void saveChanges(View v) {
        //todo save the updated alert
        ArrayList<String> check_info = new ArrayList<String>();
        for (CheckBox c1 : check1) {
            if (c1.isChecked())
                check_info.add(c1.getContext().toString());
        }
        FieldsOfInterest f1 = new FieldsOfInterest(this, check_info);
        finish();
    }


}