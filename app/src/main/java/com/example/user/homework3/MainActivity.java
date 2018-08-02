package com.example.user.homework3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // флаг действия
    int action=0; // 1+ 2- 3* 4/
    // числа для действий
    double a=0;
    double b=0;
    // результат
    double res=0;
    // флаг ввода первой цифры
    boolean is_first = true;
    // флаг ввода запятой
    boolean is_comma = true;

    // поле ввода
    EditText number;
    // текст поле ввода в формате строки
    String numstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = (EditText) findViewById(R.id.ma_et_number);
        number.setText(String.valueOf(0));
        numstr=number.getText().toString();
    }

    // округление чисел
    public BigDecimal roundUp(double value, int digits){
        return new BigDecimal(""+value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    // процедура ввода чисел
    public void enterNumbers (int val){
        if (numstr.equals("0"))
            number.setText(String.valueOf(val));
        else
            number.setText(numstr + String.valueOf(val));
        numstr = number.getText().toString();
    }

    public void actions (int act){
        a = Double.parseDouble(number.getText().toString());
        action = act;
        is_first=false;
        is_comma=true;
        numstr="";
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ma_b0: { enterNumbers(0); break; }
            case R.id.ma_b1: { enterNumbers(1); break; }
            case R.id.ma_b2: { enterNumbers(2); break; }
            case R.id.ma_b3: { enterNumbers(3); break; }
            case R.id.ma_b4: { enterNumbers(4); break; }
            case R.id.ma_b5: { enterNumbers(5); break; }
            case R.id.ma_b6: { enterNumbers(6); break; }
            case R.id.ma_b7: { enterNumbers(7); break; }
            case R.id.ma_b8: { enterNumbers(8); break; }
            case R.id.ma_b9: { enterNumbers(9); break; }

            // точка
            case R.id.ma_b_comma:{
                if (is_comma) {
                    numstr = numstr + ".";
                    number.setText(numstr);
                    is_comma = false;
                }
                break;
            }

            // C
            case R.id.ma_b_c:{
                a=0;
                b=0;
                number.setText(String.valueOf(0));
                numstr=number.getText().toString();
                is_first=true;
                is_comma=true;
            }

            // действия
            case R.id.ma_b_plus:            { actions(1); break; }
            case R.id.ma_b_substraction:    { actions(2); break; }
            case R.id.ma_b_multiplication:  { actions(3); break; }
            case  R.id.ma_b_division:       { actions(4); break; }

            // равно
            case  R.id.ma_b_equally:{
                b = Double.parseDouble(number.getText().toString());
                is_first=true;
                switch (action){
                    case 1:{ res=a+b; break;
                    }
                    case 2:{res=a-b; break;}
                    case 3:{res=a*b; break;}
                    case 4:{ if (b!=0) res=a/b; break;}
                }
                number.setText(String.valueOf(roundUp(res,4)));
            }
        }
    }
}
