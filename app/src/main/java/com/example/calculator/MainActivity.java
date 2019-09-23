package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv;
    private StringBuffer text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textField);
        text = new StringBuffer(tv.getText());
        findViewById(R.id.one).setOnClickListener(this);
        findViewById(R.id.two).setOnClickListener(this);
        findViewById(R.id.three).setOnClickListener(this);
        findViewById(R.id.four).setOnClickListener(this);
        findViewById(R.id.five).setOnClickListener(this);
        findViewById(R.id.six).setOnClickListener(this);
        findViewById(R.id.seven).setOnClickListener(this);
        findViewById(R.id.eight).setOnClickListener(this);
        findViewById(R.id.nine).setOnClickListener(this);
        findViewById(R.id.AC).setOnClickListener(this);
        findViewById(R.id.del).setOnClickListener(this);
        findViewById(R.id.div).setOnClickListener(this);
        findViewById(R.id.mul).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.sub).setOnClickListener(this);
        findViewById(R.id.equal).setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.one:
                text.append("1");
                tv.setText(text);
                break;
            case R.id.two:
                text.append("2");
                tv.setText(text);
                break;
            case R.id.three:
                text.append("3");
                tv.setText(text);
                break;
            case R.id.four:
                text.append("4");
                tv.setText(text);
                break;
            case R.id.five:
                text.append("5");
                tv.setText(text);
                break;
            case R.id.six:
                text.append("6");
                tv.setText(text);
                break;
            case R.id.seven:
                text.append("7");
                tv.setText(text);
                break;
            case R.id.eight:
                text.append("8");
                tv.setText(text);
                break;
            case R.id.nine:
                text.append("9");
                tv.setText(text);
                break;
            case R.id.add:
                text.append("+");
                tv.setText(text);
                break;
            case R.id.sub:
                text.append("-");
                tv.setText(text);
                break;
            case R.id.mul:
                text.append("x");
                tv.setText(text);
                break;
            case R.id.div:
                text.append("÷");
                tv.setText(text);
                break;
            case R.id.equal:
                //modify
                break;
                default:break;
        }
    }



}