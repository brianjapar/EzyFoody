package com.example.ezyfoody.data;

import com.example.ezyfoody.model.User;

import java.util.ArrayList;

public class UserData {

    public static UserData instance = null;
    public ArrayList<User> users;

    public UserData() {
        users = new ArrayList<>();
    }

    public static UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }
}
