package com.raydairy;

public class UserData {
    private double Fat;
    private double SNF;
    private double Price;;

    public UserData(double fat, double snf, double price){
        Fat = fat;
        SNF = snf;
        Price = price;
    }

    public String getFat() {
        return Double.toString(Fat);
    }

    public void setFat(double fat) {
        Fat = fat;
    }

    public String getSNF() {
        return Double.toString(SNF);
    }

    public void setSNF(double snf) {
        SNF = snf;
    }

    public String getPrice() {
        return Double.toString(Price);
    }

    public void setPrice(double price) {
        Price = price;
    }
}

