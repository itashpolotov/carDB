package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view){
        EditText input=findViewById(R.id.editTextTextPersonName); //обращаемся в раздел res
        Log.i("MYTAG","button is clicked");
        Log.i("MYTAG",input.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //привязывает xml к нашей программе
    }
}