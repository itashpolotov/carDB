package com.example.androidprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.widget.*;

public class Calculator extends AppCompatActivity implements View.OnClickListener {
    TextView result;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    EditText number1;
    EditText number2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        result= findViewById(R.id.result);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        number1=findViewById(R.id.number1);
        number2=findViewById(R.id.number2);

       btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Double resultNumber;


      try {
          switch (v.getId()) {
              case R.id.btn1:
                  resultNumber = Double.parseDouble(number1.getText().toString()) + Double.parseDouble(number2.getText().toString());
                  result.setText(Double.toString(resultNumber));
                  break;
              case R.id.btn2:
                  resultNumber = Double.parseDouble(number1.getText().toString()) - Double.parseDouble(number2.getText().toString());
                  result.setText(Double.toString(resultNumber));
                  break;
              case R.id.btn3:
                  resultNumber = Double.parseDouble(number1.getText().toString()) * Double.parseDouble(number2.getText().toString());
                  result.setText(Double.toString(resultNumber));
                  break;
              case R.id.btn4:
                  if (Double.parseDouble(number2.getText().toString()) != 0) {
                      resultNumber = Double.parseDouble(number1.getText().toString()) / Double.parseDouble(number2.getText().toString());
                      result.setText(Double.toString(resultNumber));
                  }
                  else{
                      Toast.makeText(Calculator.this, "division by zero", Toast.LENGTH_SHORT).show();
                  }
                  break;
          }
      }catch (NumberFormatException e){

          Toast.makeText(Calculator.this,"Please fill in the fields",Toast.LENGTH_SHORT).show();
          result.setText("");
      }   /* catch (ArithmeticException e){

        Toast.makeText(Calculator.this,"Division to zero",Toast.LENGTH_SHORT).show();
    }*/
    }
}
