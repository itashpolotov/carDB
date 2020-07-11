package com.example.androidprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.widget.*;

public class CalculatorEnhanced extends AppCompatActivity implements View.OnClickListener {
    TextView result;
    Button calculate;

    EditText field1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_enhanced);
        result= findViewById(R.id.result);
        calculate = findViewById(R.id.calculate);

        field1=findViewById(R.id.number2);


    }


    @Override
    public void onClick(View v) {

     int length=field1.getText().toString().length();
     String text=field1.getText().toString();
     char symbol=' ';
     StringBuilder a=new StringBuilder();
        StringBuilder b=new StringBuilder();
        boolean isOperatorDone=false;
     for(int i=0;i<length;i++){
         if(text.charAt(i)=='+' || text.charAt(i)=='-' ||text.charAt(i)=='*' ||text.charAt(i)=='/'){
             symbol=text.charAt(i);
             isOperatorDone=true;
         }
         else if(isOperatorDone) {
             b.append(text.charAt(i));
         }else{
             a.append(text.charAt(i));
         }

    Toast.makeText(CalculatorEnhanced.this,"a= "+a+"b= "+b+"Operator= "+symbol,Toast.LENGTH_LONG);
    }
    }
}
