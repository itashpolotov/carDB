package com.example.carsql;

public class Car {
    private String carName;

    private int carPrice;
    private int id;

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car(int id, String carName, int carPrice) {
        this.carName = carName;
        this.carPrice = carPrice;
        this.id = id;
    }

    public Car(String carName, int carPrice) {
        this.carName = carName;
        this.carPrice = carPrice;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
    }
}
