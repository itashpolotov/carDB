package com.example.musicshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.example.musicshop.models.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Button minus, plus, addToCart;
    TextView quantity,priceView;

    Spinner spinner;
    ArrayList<String> spinnerArrayList=new ArrayList<>();
    ArrayAdapter<String> spinnerAdapter;
    HashMap<String,Double> database;
    String goodName;
    double price;
    ImageView goodImage;
    EditText username;
    List<Order> orderList=new ArrayList<>();

    private int amount=0;
    public static final String TAG="MyTag";


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.cart){
            Intent intent =  new Intent(MainActivity.this, OrderActivity.class);
            if(!orderList.isEmpty()){
                intent.putExtra("orders", (Serializable) orderList);
                intent.putExtra("status",1);
            }else
            {   intent.putExtra("status",0);}
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.    activity_main);

      //variable initialization
        minus=findViewById(R.id.minus);
        plus=findViewById(R.id.plus);
       quantity=findViewById(R.id.quantity_text);
       priceView=findViewById(R.id.price);
       spinner=findViewById(R.id.spinner);
       spinner.setOnItemSelectedListener(this);
       goodImage=findViewById(R.id.goodImage);
       addToCart=findViewById(R.id.addToCart);
       username=findViewById(R.id.username);

        //Arraylist for spinner
       spinnerArrayList.add("guitar");
        spinnerArrayList.add("keyboard");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("rock");

        //Adapter for spinner
        spinnerAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        //Database for spinner
        database=new HashMap<>();
        database.put("guitar",500.0);
        database.put("keyboard",1000.0);
        database.put("drums",700.0);
        database.put("rock",1500.0);


        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        addToCart.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodName=spinner.getSelectedItem().toString();//guitar
        price=database.get(goodName);
        priceView.setText(String.valueOf(price));
        amount=1;
        quantity.setText("1");

        switch (goodName){
            case "guitar":
                goodImage.setImageResource(R.drawable.guitar);
                break;
            case "drums":
                goodImage.setImageResource(R.drawable.drums);
                break;
            case "keyboard":
                goodImage.setImageResource(R.drawable.keyboard);
                break;
            case "rock":
                goodImage.setImageResource(R.drawable.rock);
                break;

        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.plus:
                amount++;
                quantity.setText(String.valueOf(amount));
                priceView.setText(String.valueOf(price*amount));
                break;
            case R.id.minus:
                amount--;
                if (amount<0) {
                    amount = 0;
                }
                quantity.setText(String.valueOf(amount));
                priceView.setText(String.valueOf(price*amount));
                break;
            case R.id.addToCart:
                orderProcess();
                break;
        }
    }
    public void orderProcess(){
        Order order= new Order();
        if (!TextUtils.isEmpty(username.getText().toString())) {
            order.setUserName(username.getText().toString());
            order.setGoodName(spinner.getSelectedItem().toString());
            order.setGoodPrice(Double.parseDouble(priceView.getText().toString()));
            order.setGoodQuantity(Integer.parseInt(quantity.getText().toString()));

          //Checking existance of other orders of similar goods
           if (orderList.size()>0){

               Order result=isHas(goodName,orderList);

               if(result!=null){


                      result.setGoodQuantity(result.getGoodQuantity()+order.getGoodQuantity());
                      result.setGoodPrice(result.getGoodPrice()+order.getGoodPrice());

                   }else{orderList.add(order);}
               }

           else{            orderList.add(order);}

            Log.i(TAG,order.toString());

            Toast.makeText(this,"Вы успешно добавили товар в корзину",Toast.LENGTH_SHORT).show();

            username.setText("");
            spinner.setSelection(0);
            goodName=spinner.getSelectedItem().toString();//guitar
            price=database.get(goodName);
            priceView.setText(String.valueOf(price));
            amount=1;
            quantity.setText("1");


        } else {
            Toast.makeText(this,"Пожалуйста заполните все поля",Toast.LENGTH_SHORT).show();
        }

    }
    public Order isHas(String goodName, List<Order> orderList){
        for(Order order:orderList){
            if(order.getGoodName().equals(goodName)){
                return order;
            }
        }
        return null;
    }
}