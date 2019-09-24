package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv;
    private TextView rs;
    private StringBuffer text;
    private StringBuffer result;
    private Button btn;
    private int flag;
    private boolean point_flag;//判断上一个符号是否是. ，如果是，就不能再加. ,如果不是,就可以加.
    /*
        flag
        0：只能输入数字  str = （0-9）
        1：可以输入数字符号 str = 1 ( （1-9） + - × / .)
        2：只可以输入. str = 5 / 0 (.)
        3：可以输入（ + - × / .）

        AC之后返回0状态
        del之后
            如果str为空 返回状态0
            如果str最后一个为数字(1-9) 返回状态1
            如果str最后一个为数字0 返回状态3
            如果str最后一个为. 返回状态0
            如果str最后一个为（ + - × / ）返回状态0
        对于0
            前面是除号，添加0，提示不能除于0，返回状态2
            前面是数字，添加0，返回状态0
            前面是特殊符号，添加0，返回状态2
            前面没有，添加0，返回状态3
        对于.
            前面没有，不可以添加，保持原状态
            前面是特殊符号，不可以添加，保持原状态
            如果point_flag == false:
                前面是数字，添加. ，point_flag = true，返回状态0
            如果point_flag == true:
                不可以添加，保持原状态
        对于（+ - × /）
            前面没有，不可以添加，保持原状态
            前面是特殊符号，将最后一个符号更改为当前的符号，保持原状态
            前面是数字，添加符号，返回状态0


     */




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textField);
        rs = (TextView) findViewById(R.id.result);
        text = new StringBuffer(tv.getText());
        result = new StringBuffer(rs.getText());

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
        rs.setText("");
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
                if(flag != 3){ //0后面只能是.或者
                    text.append(btn.getText());
                    tv.setText(text);
                    flag = 1;   //输入数字之后可以继续加入符号
                }


                break;
            case R.id.zero:

                if( text.length() > 0 && text.charAt(text.length() - 1) == '÷'){
                    text.append("0");
                    result.append("\n不能除于0");
                    tv.setText(text);
                    rs.setText(result);
                    result.delete(0, result.length());
                    flag = 3; //输入0之后只能输入.
                    break;
                }
                btn = findViewById(v.getId());
                text.append(btn.getText());
                tv.setText(text);
                flag = 1;   //输入数字之后可以继续加入符号
                break;

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
                else if(flag == 2) {
                    text.deleteCharAt(text.length() - 1);
                    text.append(btn.getText());
                    tv.setText(text);
                    flag = 2;
                }

                break;
            case R.id.point:
                btn = findViewById(v.getId());
                if(flag == 0 && text.charAt(text.length() - 1) == '0'){
                    text.append(btn.getText());
                    tv.setText(text);
                    flag = 0;
                    break;
                }
                else if(flag == 1){ //可以输入特殊符号
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
                else if(flag == 3){
                    text.deleteCharAt(text.length() - 1);
                    text.append(btn.getText());
                    tv.setText(text);
                    flag = 2;
                }
            case R.id.AC:
                text.delete(0,text.length());
                tv.setText(text);
                flag = 0;
                break;
            case R.id.del:
                if(text.length() > 0){
                    text.deleteCharAt(text.length()-1);
                    tv.setText(text);
                    flag = 1;
                    break;
                }
                break;
            case R.id.equal:
                //
                break;
                default:break;
        }
    }



}