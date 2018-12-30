package com.kalay.shift.shift;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class InterestPage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
    }
    public void first_interst(View v){
        Intent intent = new Intent(getApplicationContext(), testi.class);
        startActivity(intent);
    }
    public void second_interst(View x){
        Intent intent = new Intent(getApplicationContext(), testi.class);
        startActivity(intent);
    }
    public void  third_interst(View z){
        Intent intent = new Intent(getApplicationContext(), testi.class);
        startActivity(intent);
    }
    public void fourth_interst(View y){
        Intent intent = new Intent(getApplicationContext(), testi.class);
        startActivity(intent);
    }
}