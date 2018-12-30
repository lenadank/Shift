package com.kalay.shift.shift;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.mcsoft.timerangepickerdialog.RangeTimePickerDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romdolinger on 4/14/18.
 */

public class AddPersonalTime extends AppCompatActivity implements RangeTimePickerDialog.ISelectedTime {

    int i = AlertsSaver.startKey;
    static final String names[] = {"שבת", "שישי", "חמישי", "רביעי", "שלישי", "שני", "ראשון"};
    static CheckBox[] daysArr = new CheckBox[7];
    Spinner dropdown;
    List<Integer> keyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_time);
        //create a list of items for the spinner.
        List<Object> myList = new ArrayList<>();
        myList.add("Please select an Item");
        List<String> deleted = AlertsSaver.returnDeltedPlaces(this);
        AlertsSaver alert;
        while (true) {
            try {
                //Creates alert array and key array
                if (deleted.size() == 0 || (deleted.size() != 0 && !deleted.contains(getString(i)))) {
                    alert = new AlertsSaver(this, Integer.toString(i));
                    myList.add(alert.getAlertTitle());
                    keyList.add(i);
                }
                i++;
            } catch (Exception e) {
                break;
            }
        }

        //read input array
        for (int j = 0; j < names.length; j++) {
            //create the UI check box
            final LinearLayout ll = findViewById(R.id.linearLayoutId);
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(names[j]);
            ll.addView(cb);
            daysArr[j] = cb;
        }
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.

        dropdown = findViewById(R.id.spinner);
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<Object> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, myList);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        FloatingActionButton b1 = (FloatingActionButton) findViewById(R.id.fab);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an instance of the dialog fragment and show it
                RangeTimePickerDialog dialog = new RangeTimePickerDialog();
                dialog.newInstance();
                dialog.setRadiusDialog(20); // Set radius of dialog (default is 50)
                dialog.setIs24HourView(true); // Indicates if the format should be 24 hours
                dialog.setColorBackgroundHeader(R.color.colorPrimary); // Set Color of Background header dialog
                dialog.setColorTextButton(R.color.colorPrimaryDark); // Set Text color of button
                FragmentManager fragmentManager = getFragmentManager();
                dialog.show(fragmentManager, "");
            }
        });

    }

    public void onClick1(View v) {
        //todo save the updated alert days
        boolean[] days = new boolean[7];
        for (int i = 0; i < days.length; i++)
            days[i] = daysArr[i].isChecked();
        int listCount = dropdown.getSelectedItemPosition();
        if (listCount > 0) {
            AlertsSaver alert = new AlertsSaver(this, Integer.toString(keyList.get(listCount - 1)));
            alert.setDays(this, days);
            Toast.makeText(this, "YOUR DAYS HAVE BEEN SAVED", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "PLEASE SELECT AN ITEM", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSelectedTime(int hourStart, int minuteStart, int hourEnd, int minuteEnd) {
        //todo save the updated alert hours
        int listCount = dropdown.getSelectedItemPosition();
        if (listCount > 0) {
            AlertsSaver alert = new AlertsSaver(this, Integer.toString(keyList.get(listCount - 1)));
            String[] arr = new String[2];
            arr[0] = Integer.toString(hourStart) + ":" +
                    Integer.toString(minuteStart);
            arr[1] = Integer.toString(hourEnd) + ":" +
                    Integer.toString(minuteEnd);
            alert.setHours(this, arr);
            Toast.makeText(this, alert.toString(), Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "PLEASE SELECT AN ITEM", Toast.LENGTH_SHORT).show();

    }

    public void moveToHome(View v) {
        //todo moves to HomePage class
        Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
        startActivity(intent);
    }

}
