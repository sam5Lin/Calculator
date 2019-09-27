package com.example.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.Vector;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv;
    private TextView rs;
    private int length;
    private StringBuffer text;
    private StringBuffer result;
    private Button btn;
    private int flag;
    private boolean point_flag;//判断上一个符号是否是. ，如果是，就不能再加. ,如果不是,就可以加.
    /*
        flag
            0：只能输入数字  str = （0-9）
            1：可以输入数字符号 str = 1 ( （0-9） + - × / .)
            2：只可以输入. str = 5 / 0 (.)
            3：可以输入（ + - × / . ）
            4：可以输入 (+ - x / (0-9))

        point_flag
            false：当前的数字没有.
            true：当前的数字有.

        AC之后返回0状态
        del之后
            如果str为空 返回状态0
            如果str最后一个为数字(1-9) 返回状态1
            如果str最后一个为数字0 返回状态3
            如果str最后一个为. 返回状态0
            如果str最后一个为（ + - × / ）返回状态0
        对于0
            前面是除号，添加0，提示不能除于0，返回状态2
            前面是数字，添加0，返回状态1
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
            前面是数字，添加符号，返回状态4


     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textField);
        rs = (TextView) findViewById(R.id.result);
        text = new StringBuffer(tv.getText());
        result = new StringBuffer(rs.getText());

        findViewById(R.id.point).setOnClickListener(this);
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
        point_flag = false;


    }

    @Override
    public void onClick(View v){
        length = text.length();
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
                if(flag == 0 || flag == 1 || flag == 4 || (flag == 3 && point_flag == true)){
                    text.append(btn.getText());
                    tv.setText(text);
                    flag = 1;
                }
                result = new StringBuffer(evaluate(text));

                rs.setText(result);
                break;
            case R.id.zero:
                if(flag == 0 || flag == 1 || flag == 4){
                    if(length > 0){
                        char character = text.charAt(length - 1);
                        if(character == '÷'){
                            text.append("0");
                            result.delete(0,result.length());
                            result.append("不能除于0");

                            Log.e("e----->", result+" ");
                            tv.setText(text);
                            rs.setText(result);
                            result.delete(0, result.length());
                            flag = 2;
                            break;
                        }
                        else if(isDigital(character)){
                            text.append("0");
                            tv.setText(text);
                            flag = 1;
                            result = new StringBuffer(evaluate(text));
                            rs.setText(result);
                        }
                        else if(character == '.'){
                            text.append("0");
                            tv.setText(text);
                            point_flag = true;
                            flag = 4;
                            result = new StringBuffer(evaluate(text));
                            rs.setText(result);

                        }
                        else if(isCharacter(character)){
                            text.append("0");
                            tv.setText(text);
                            flag = 3;
                            result = new StringBuffer(evaluate(text));
                            rs.setText(result);
                        }
                    }
                    else {
                        text.append("0");
                        tv.setText(text);
                        flag = 3;
                        result = new StringBuffer(evaluate(text));
                        rs.setText(result);
                    }
                }

                break;

            case R.id.add:
            case R.id.sub:
            case R.id.div:
            case R.id.mul:
                btn = findViewById(v.getId());
                if(flag == 1 || flag == 3 || flag == 4){
                    if(length > 0){
                        char character = text.charAt(length - 1);
                        if(isCharacter(character)){
                            text.deleteCharAt(length - 1);
                            text.append(btn.getText());
                            tv.setText(text);
                            break;
                        }
                        else if(isDigital(character)){
                            text.append(btn.getText());
                            tv.setText(text);
                            flag = 4; // 3+((0-9)||(+-x/))
                            point_flag = false; //可以加.
                            break;
                        }
                    }
                }

                break;
            case R.id.point:
                btn = findViewById(v.getId());
                if(flag == 1 || flag == 2 || flag == 3){
                    if(length > 0){
                        char character = text.charAt(length - 1);
                        if(point_flag == false && isDigital(character)){
                            text.append(btn.getText());
                            tv.setText(text);
                            point_flag = true;
                            flag = 0;
                            break;

                        }
                    }
                }
                result = new StringBuffer(evaluate(text));
                rs.setText(result);
                break;
            case R.id.AC:
                text.delete(0,text.length());
                tv.setText(text);
                result = new StringBuffer("");
                rs.setText(result);
                point_flag = false;
                flag = 0;
                break;
            case R.id.del:
                if(text.length() > 0){
                    char character = text.charAt(length - 1);
                    length -= 1;
                    text.deleteCharAt(text.length()-1);
                    if(isCharacter(character)){
                        point_flag = findPreviousPoint();
                    }

                    tv.setText(text);

                    if(length == 0){
                        flag = 0;
                    }
                    else if(length > 0){
                        character = text.charAt(length - 1);
                        if(isoneTonine(character)){
                            flag = 1;
                        }
                        else if(iszero(character)){
                            flag = 3;
                        }
                        else if(character == '.'){
                            point_flag = false;
                            flag = 0;
                        }
                        else if(isCharacter(character)){
                            flag = 1;
                        }
                    }
                    if(text.length() > 0){
                        result = new StringBuffer(evaluate(text));
                        rs.setText(result);
                    }
                    else{
                        result = new StringBuffer("");
                        rs.setText(result);
                    }

                    break;
                }

                break;
            case R.id.equal:
                result = new StringBuffer(evaluate(text));

                Log.e("pointflag", point_flag+" ");
                if(result.toString().equals("不能除于0")){
                    flag = 2;
                    if(text.charAt(text.length() - 1) == '.'){
                        flag = 0;
                    }
                }
                else{
                    text = result;
                    tv.setText(text);
                    if(result.toString().equals("0")){
                        flag = 1;
                    }
                }
                rs.setText(result);
                point_flag = havePoint(result);
                break;
                default:break;
        }
    }

    private boolean havePoint(StringBuffer result){
        for(int i = 0;i < result.length();i++){
            if(result.charAt(i) == '.'){
                return true;
            }
        }
        return false;
    }

    private boolean isDigital(char c){
        if(c >= '0' && c <= '9'){
            return true;
        }
        return false;
    }


    private boolean isoneTonine(char c){
        if(c >= '1' && c <= '9'){
            return true;
        }
        return false;
    }

    private boolean iszero(char c){
        if(c == '0'){
            return true;
        }
        return false;
    }

    private boolean isCharacter(char c){
        if(c == '÷' || c == '×' || c == '+' || c == '-'){
            return true;
        }
        return false;
    }

    private int findPreviousCharacterIndex(){
        for(int i = length - 1; i >= 0; i--){
            if(isCharacter(text.charAt(i))){
                return i;
            }
        }
        return -1;
    }

    private boolean findPreviousPoint(){
        int index = findPreviousCharacterIndex();
        if(index == -1){ //如果找不到上一个运算符
            for(int i = length - 1; i >= 0; i--){
                if(text.charAt(i) == '.'){  //如果之前已经有小数点
                    return true;
                }
            }
        }
        else{//如果找到上一个运算符
            for(int i = length - 1; i >= index; i--){
                if(text.charAt(i) == '.'){  //如果之前已经有小数点
                    return true;
                }
            }
        }
        return false;
    }


    /*
    中序转后序
     */
    public  Vector<String> transform(StringBuffer s){
        if(s.length() == 0){ //text为空
            return null;
        }
        StringBuffer str = new StringBuffer(s);
        if(str.charAt(0) == '-'){
            str.insert(0,'0');
        }
        length = str.length();
        if(isCharacter(str.charAt(str.length() - 1))){//最后一个为特殊字符
            length -= 1;
        }
        Vector<String> st = new Vector<>();
        Stack<Character> ops = new Stack<>();    //运算符栈

        String num_str = "";
        for(int i = 0 ;i < length ; i++){
            char ch = str.charAt(i);

            if((ch >= '0' && ch <= '9') || ch == '.'){
                num_str += ch;
            }
            else if(isCharacter(ch)){
                st.add(num_str);
                num_str = "";
                while(true){
                    if(ops.isEmpty()){
                        ops.push(ch);
                        break;
                    }
                    char pre = ops.peek();
                    if((pre == '+' || pre == '-') && (ch == '×' || ch == '÷')){ //5+3-2
                        ops.push(ch);
                        break;
                    }
                    st.add(String.valueOf(ops.pop()));
                }
            }
        }
        st.add(num_str);


        while(!ops.isEmpty()){
            st.add(String.valueOf(ops.pop()));
        }
        return st;
    }

    //计算
    public String evaluate(StringBuffer str){
        Vector<String> s = transform(str);    //中序表达式转换为后序表达式
        if(s == null){
            return "";
        }
        Stack<BigDecimal> num = new Stack<>();
        for(int i = 0; i < s.size(); i++){
            String c = s.get(i);

            if(!isCharacter(c.charAt(0))){
                num.push(new BigDecimal(c));
            }
            else{

                BigDecimal d1 = num.pop();
                BigDecimal d2 = num.pop();

                switch(c.charAt(0)){
                    case '+':

                        num.push(d1.add(d2));
                        break;
                    case '-':

                        num.push(d2.subtract(d1));
                        break;
                    case '×':

                        num.push(d1.multiply(d2));
                        break;
                    case '÷':

                        if(d1.doubleValue() != 0){

                            if(d2.divide(d1,9, BigDecimal.ROUND_HALF_UP).doubleValue() == (d2.doubleValue() / d1.doubleValue())){
                                num.push(new BigDecimal(d2.doubleValue() / d1.doubleValue()));
                            }
                            else {
                                num.push(d2.divide(d1,9, BigDecimal.ROUND_HALF_UP));
                            }
                        }
                        else{
                            return "不能除于0";
                        }
                        break;
                    default:break;
                }
            }
        }
        return num.pop().stripTrailingZeros().toString();
        //return num.pop().toString();

    }

}