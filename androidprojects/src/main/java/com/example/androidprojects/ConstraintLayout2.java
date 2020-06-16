package com.example.androidprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class ConstraintLayout2 extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    Button btn1;
    Button btn2;
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout2);

        textView = findViewById(R.id.text);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Button 1 is pressed");
            }


        });
   //     btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
         /*   case R.id.btn2:
                textView.setText("Button2 is pressed");
                break;*/
            case R.id.btn3:
                textView.setText("Button3 is pressed");
                break;
        }
    }
        public void onClickBtn2(View v){
            textView.setText("Btn 2 is pressed");
        }
    }
