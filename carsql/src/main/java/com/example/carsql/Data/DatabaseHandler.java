package com.example.carsql.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.carsql.Car;
import com.example.carsql.Util.Util;

import java.util.ArrayList;
import java.util.List;

        public class DatabaseHandler extends SQLiteOpenHelper {
        public DatabaseHandler(@Nullable Context context) {
            super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
        }

        //AUTO INCREMENT
        @Override
        public void onCreate(SQLiteDatabase db) { //used to create the DB
            //SQL Structured Query Language
            String CREATE_CARS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                    + Util.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Util.KEY_NAME + " TEXT,"
                    + Util.KEY_PRICE + " TEXT" + ")";
            db.execSQL(CREATE_CARS_TABLE);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
            onCreate(db);
        }

        public void addCar(Car car) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues(); //needed to write data
            contentValues.put(Util.KEY_NAME, car.getCarName());
            contentValues.put(Util.KEY_PRICE, car.getCarPrice());

            db.insert(Util.TABLE_NAME, null, contentValues);
            db.close();
        }

        //Read-{id}-> Car, List<Car>
        public Car getCar(int id) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_PRICE}, Util.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
            Car car = new Car();
            if (cursor != null) {
                try {
                    cursor.moveToFirst();

                    car = new Car(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)));
                } finally {
                    cursor.close();
                }

            }
            return car;
        }

        public List<Car> getAllCars() {
            SQLiteDatabase db = this.getReadableDatabase();

            List<Car> carsList = new ArrayList<>();

            String selectAllCars = "SELECT * FROM " + Util.TABLE_NAME;
            Cursor cursor = db.rawQuery(selectAllCars, null);
            if (cursor.moveToFirst()) {
                try {
                    do {
                        Car car = new Car();
                        car.setId(Integer.parseInt(cursor.getString(0)));
                        car.setCarName(cursor.getString(1));
                        car.setCarPrice(Integer.parseInt(cursor.getString(2)));
                        carsList.add(car);
                    } while (cursor.moveToNext()); //while cursor can to move to the next element
                } finally {
                    cursor.close();
                }
            }
            return carsList;
        }

        public int updateCar(Car car) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Util.KEY_NAME, car.getCarName());
            contentValues.put(Util.KEY_PRICE, car.getCarPrice());
            return db.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "=?", new String[]{String.valueOf(car.getId())});

        }

        public void deleteCar(Car car) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?", new String[]{String.valueOf(car.getId())});
            db.close();
        }
        public void deleteAllCars(){
            SQLiteDatabase db=this.getWritableDatabase();
            db.execSQL("DELETE FROM "+Util.TABLE_NAME);
            db.close();
        }
    }

