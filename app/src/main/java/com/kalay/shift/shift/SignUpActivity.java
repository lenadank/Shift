package com.kalay.shift.shift;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

    public void enterName(View v){
        editName.getText().clear();
    }

    public void checkButton(View v) {
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







