package com.kalay.shift.shift.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kalay.shift.shift.Classes.Alert;
import com.kalay.shift.shift.Classes.Interests;
import com.kalay.shift.shift.Classes.SharedPreferencesManager;
import com.kalay.shift.shift.R;

import java.util.ArrayList;
import java.util.List;


public class InterestsHandlerActivity extends Activity {
    private Interests interests = new Interests();
    private List<Pair<String, Integer>> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests_handler);
        ListView notificationListView = (ListView) findViewById(R.id.notificationListView);
        notificationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(), "!!!!!" + position, Toast.LENGTH_SHORT).show();
                //Alert alert = new Alert(null, null, null, "test Title");
                Intent myIntent = new Intent(getApplicationContext(), EditPersonalTimeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("interest", items.get(position).first);
                bundle.putInt("position", items.get(position).second);
                myIntent.putExtras(bundle);
                startActivity(myIntent);
            }
        });
        SharedPreferencesManager manager = SharedPreferencesManager.getInstance();
        interests = (Interests) manager.getStoredData(InterestsHandlerActivity.this, "Interests", Interests.class);

        if (interests == null) {
            interests = new Interests();
        }

//        interests.addInterest("check");
//
//        interests.addNotification("test", new Alert(null, null, null, "test Title"));
//        interests.addNotification("test", new Alert(null, null, null, "test Title 2.0"));
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferencesManager manager = SharedPreferencesManager.getInstance();
        interests = (Interests) manager.getStoredData(InterestsHandlerActivity.this, "Interests", Interests.class);

        ListView notificationListView = findViewById(R.id.notificationListView);
        List<String> notificationTitles = new ArrayList<>();
        List<String> interestList = interests.getInterests();
        for (String interest : interestList) {
            if (interests.getNotifications(interest).isEmpty()) {
//                items.add(new Pair<String, Integer>(interest, null));
            }
            for (int index = 0; index < interests.getNotifications(interest).size(); index++) {
                items.add(new Pair<>(interest, index));
            }
        }


        for (Pair<String, Integer> item : items) {
            if (item.second == null) {
                notificationTitles.add(item.first + "\n(No Notification)");
            } else {
                List<Alert> notifications = interests.getNotifications(item.first);
                Alert alert = notifications.get(item.second);
                Pair<Integer, Integer> time = alert.getHours();
                notificationTitles.add(/*item.first + "\n" +*/ alert.getAlertTitle() + "\n" + alert.getDaysCompactRep() + " " + alert.getTimeAsString());
            }
        }
        interests.save(this);
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notificationTitles);
        notificationListView.setAdapter(itemsAdapter);
    }

    public void onClick(View t) {
        ListView notificationListView = (ListView) findViewById(R.id.notificationListView);
        //interests.addNotification(null,null);
        Intent myIntent = new Intent(getApplicationContext(), EditPersonalTimeActivity.class);
        Bundle bundle = new Bundle();
//        String interest = interests.getInterests().get(notificationListView.getFooterViewsCount());
        bundle.putString("interest", "***");
        bundle.putInt("position", -1);
        myIntent.putExtras(bundle);
        startActivity(myIntent);
    }
}
