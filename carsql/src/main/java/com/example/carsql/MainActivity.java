package com.example.carsql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carsql.Data.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Car> carList = new ArrayList<>();
    CAdapter carAdapter;
    DatabaseHandler handler = new DatabaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



 /*  handler.addCar(new Car("Toyota", 35000));
        handler.addCar(new Car("KIA", 31000));
        handler.addCar(new Car("MAZDA", 22000));*/

       carList = handler.getAllCars();



        carAdapter = new CAdapter(MainActivity.this, carList);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(carAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                insertItem();
            }
        }); //add a new car


        carAdapter.setOnItemClickListener(new CAdapter.RecyclerOnClickListener() { //edit or delete an existing item

            @Override
            public void onItemClick(int position) {
                editItem(position);

            }
    });
    }



    public void insertItem() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this); //создания окна уведомления
        dialog.setTitle("Add car");


        View layout = LayoutInflater.from(this).inflate(R.layout.add_car, null);

        final EditText carName = layout.findViewById(R.id.carName);
        final EditText carPrice = layout.findViewById(R.id.carPrice);

        dialog.setView(layout);

        dialog.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(carName.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please enter car name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(carPrice.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please enter car price", Toast.LENGTH_SHORT).show();
                    return;
                }
                carList.add(0, new Car(carName.getText().toString(), Integer.parseInt(carPrice.getText().toString())));
                //   Log.i("MYTAG",carList.get(0).getCarName());
                carAdapter.notifyItemInserted(0);
                handler.addCar(carList.get(0));

            }
        });
        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }
    public void editItem(final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this); //создания окна уведомления
        dialog.setTitle("Edit car");


        View layout = LayoutInflater.from(this).inflate(R.layout.add_car, null);

        final EditText carName = layout.findViewById(R.id.carName);
        final EditText carPrice = layout.findViewById(R.id.carPrice);
        carName.setText(carList.get(position).getCarName());
        carPrice.setText(String.valueOf(carList.get(position).getCarPrice()));

        dialog.setView(layout);

        dialog.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(carName.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please enter car name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(carPrice.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please enter car price", Toast.LENGTH_SHORT).show();
                    return;
                }
                carList.get(position).setCarName(carName.getText().toString());
                carList.get(position).setCarPrice(Integer.parseInt(carPrice.getText().toString()));
              //  Log.i("MYTAG",String.valueOf(carName));
                handler.updateCar(carList.get(position));
                carAdapter.notifyDataSetChanged();

            }
        });
        dialog.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.deleteCar(carList.get(position));
                carList.remove(position);
                carAdapter.notifyItemRemoved(position);
                dialog.dismiss();
            }
        });


        dialog.show();
    }
}
