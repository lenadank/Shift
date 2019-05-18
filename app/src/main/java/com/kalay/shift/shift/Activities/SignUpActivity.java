package com.kalay.shift.shift.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.kalay.shift.shift.Classes.SharedPreferencesManager;
import com.kalay.shift.shift.Classes.User;
import com.kalay.shift.shift.R;

public class SignUpActivity extends Activity {

    EditText editName;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editName = findViewById(R.id.editName);
        radioGroup = findViewById(R.id.radioGroup);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RadioButton maleButton = findViewById(R.id.male);
        RadioButton femaleButton = findViewById(R.id.female);
        RadioButton differntButton = findViewById(R.id.differnt);

        SharedPreferencesManager manager = SharedPreferencesManager.getInstance();
        User u = (User) manager.getStoredData(SignUpActivity.this, "User", User.class);
        if (u != null){
            editName.setText(u.getName());
            if (u.getGender().equals("זכר")){
                maleButton.setChecked(true);
            }else if (u.getGender().equals("נקבה")){
                femaleButton.setChecked(true);
            }else {
                differntButton.setChecked(true);
            }
        }

    }

    public void checker(View v){
        String name = editName.getText().toString();
        int radioId = radioGroup.getCheckedRadioButtonId();

        String error = "";

        if ((name.equals("")) && (radioId == -1)){
            error = "אנא הכניס\\י שם ומין";
        }
        if ((!name.equals("")) && (radioId == -1)){
            error = "אנא הכניס\\י מין";
        }
        if ((name.equals("")) && (radioId != -1)){
            error = "אנא הכניס\\י שם";
        }
        if ((!name.equals("")) && (radioId != -1)) {
            signUpButton(v);
        }

        if (!error.equals("")){
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    public void signUpButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        String name = editName.getText().toString().trim();
        String gender = radioButton.getText().toString();
        User user = new User(name, gender);
        user.save(this);

        Intent intent = new Intent(getApplicationContext(), InterestsHandlerActivity.class);
        startActivity(intent);
    }
}







