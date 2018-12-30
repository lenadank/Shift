package com.kalay.shift.shift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignUpActivity extends AppCompatActivity {

    Button bRegister ;
    EditText editName;
    RadioGroup radioGroup;
    RadioButton radioButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


    editName = (EditText) findViewById(R.id.editName);
    radioGroup = findViewById(R.id.radioGroup);}
    public void checkButton(View v){
        int radioId =  radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
    bRegister = (Button) findViewById(R.id.bRegister);
    bRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bRegister:

                    String name = editName.getText().toString();
                    String gender = radioButton.getText().toString();

                    User user = new User(name, gender);
                    new PersonalInfo(SignUpActivity.this, user);
                    break;

        }
    };
    });
        Intent intent = new Intent(getApplicationContext(), InterestsActivity.class);
        startActivity(intent);
    }
}







