package com.kalay.shift.shift.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kalay.shift.shift.Classes.Alert;
import com.kalay.shift.shift.Classes.Interests;
import com.kalay.shift.shift.R;

import java.util.ArrayList;
import java.util.List;


public class InterestsHandlerActivity extends AppCompatActivity {
    private Interests interests = new Interests();
    private List<Pair<String, Alert>> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests_handler);
        ListView notificationListView = (ListView) findViewById(R.id.notificationListView);
        notificationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "!!!!!" + position, Toast.LENGTH_SHORT).show();
            }
        });


        interests.addInterest("check");

        interests.addNotification("test", new Alert(null, null, null, "test Title"));
        interests.addNotification("test", new Alert(null, null, null, "test Title 2.0"));


    }

    @Override
    protected void onStart() {
        super.onStart();

        ListView notificationListView = findViewById(R.id.notificationListView);
        List<String> notificationTitles = new ArrayList<>();
        List<String> interestList = interests.getInterests();
        for (String interest : interestList) {
            if (interests.getNotifications(interest).isEmpty()) {
                items.add(new Pair<String, Alert>(interest, null));
            }
            for (Alert alert : interests.getNotifications(interest)) {
                items.add(new Pair<>(interest, alert));
            }
        }

        for (Pair<String, Alert> item:items){
            if (item.second == null){
                notificationTitles.add(item.first + "\n(No Notification)");
            }
            else{
                notificationTitles.add(item.first + "\n" + item.second.getAlertTitle());
            }
        }
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notificationTitles);
        notificationListView.setAdapter(itemsAdapter);


    }
}
