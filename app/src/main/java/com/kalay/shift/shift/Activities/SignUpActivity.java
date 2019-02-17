package com.kalay.shift.shift.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.kalay.shift.shift.Classes.PersonalInfo;
import com.kalay.shift.shift.R;
import com.kalay.shift.shift.Classes.User;

public class SignUpActivity extends Activity {

    Button bRegister;
    EditText editName;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editName = (EditText) findViewById(R.id.editName);
        radioGroup = findViewById(R.id.radioGroup);
    }

    public void checker(View v){
        String name = editName.getText().toString();
        int radioId = radioGroup.getCheckedRadioButtonId();
        final TextView ErrorText = (TextView) findViewById(R.id.ErrorView);


        if ((name.equals("")) && (radioId == -1)){
            ErrorText.setText("אנא תכניס שם ומין");
            ErrorText.setVisibility(View.VISIBLE);
        }
        if ((!name.equals("")) && (radioId == -1)){
            ErrorText.setText("אנא תכניס מין");
            ErrorText.setVisibility(View.VISIBLE);
        }
        if ((name.equals("")) && (radioId != -1)){
            ErrorText.setText("אנא תכניס שם");
            ErrorText.setVisibility(View.VISIBLE);
        }
        if ((!name.equals("")) && (radioId != -1)) {
            signUpButton(v);
        }



    }

    public void signUpButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        String name = editName.getText().toString();
        String gender = radioButton.getText().toString();
        User user = new User(name, gender);
        new PersonalInfo(SignUpActivity.this, user);

        Intent intent = new Intent(getApplicationContext(), InterestsActivity.class);
        startActivity(intent);
    }
}







