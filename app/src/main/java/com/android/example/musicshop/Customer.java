package com.android.example.musicshop;

import java.sql.SQLOutput;

public class Customer {
    Order order = new Order();
    String name;

    public boolean hasDiscount(){
        if (order.orderPrice >= 1000){
            return true;
        }
        else
            return false;
    }

    public void saleInformationInOrderActivity(){
        if (hasDiscount()){
            System.out.println("Hello " + name + "your order is more than 1000$, you have 30% discount!");
        }
        else{
            System.out.println("Hello " + name + "you can buy something else and get 30% discount!");
        }
    }

}
