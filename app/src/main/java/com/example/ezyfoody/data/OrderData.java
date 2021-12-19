package com.example.ezyfoody.data;

import com.example.ezyfoody.model.Order;

import java.util.ArrayList;

public class OrderData {
    public static OrderData instance = null;
    public ArrayList<Order> orders;

    public OrderData() {
        orders = new ArrayList<>();
    }

    public static OrderData getInstance() {
        if (instance == null) {
            instance = new OrderData();
        }
        return instance;
    }
}
