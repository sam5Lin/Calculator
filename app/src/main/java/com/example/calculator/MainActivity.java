package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv;
    private StringBuffer text;
    private Button btn;
    private int flag;//设置flag判断输入特殊符号的情况
                    // 0:不能输入特殊符号 1：可以输入特殊符号 2：可以更改最后一个特殊符号




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textField);
        text = new StringBuffer(tv.getText());

        findViewById(R.id.zero).setOnClickListener(this);
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

        flag = 0;
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){

            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
                btn = findViewById(v.getId());
                text.append(btn.getText());
                tv.setText(text);
                flag = 1;   //输入数字之后可以继续加入符号
                break;
            case R.id.zero:
                if(text.charAt(text.length() - 1) == '÷'){
                    text.append("0");
                    text.append("\n");
                    text.append("不能除于0");
                    tv.setText(text);
                    text.delete(0,text.length());
                    flag = 0;
                    break;
                }
                btn = findViewById(v.getId());
                text.append(btn.getText());
                tv.setText(text);
                flag = 1;   //输入数字之后可以继续加入符号
                break;
            case R.id.point:
            case R.id.add:
            case R.id.sub: //减号可以是第一个 当text为空时，可以有减号
            case R.id.div:
            case R.id.mul:
                btn = findViewById(v.getId());
                if(flag == 1){ //可以输入特殊符号
                    text.append(btn.getText());
                    tv.setText(text);
                    flag = 2;
                }
                else if(flag == 2){
                    text.deleteCharAt(text.length() - 1);
                    text.append(btn.getText());
                    tv.setText(text);
                    flag = 2;
                }

                break;
            case R.id.AC:
                text.delete(0,text.length());
                tv.setText(text);
                System.out.println(text);
                break;
            case R.id.del:
                System.out.println(text);
                if(text.length() > 0){
                    text.deleteCharAt(text.length()-1);
                    tv.setText(text);

                }
                break;
            case R.id.equal:
                //
                break;
                default:break;
        }
    }



}