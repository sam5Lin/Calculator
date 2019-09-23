package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public  void myclick(View v){
        Toast.makeText(getApplicationContext(), "button is clicked",  Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {

    }
}