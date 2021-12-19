package com.example.ezyfoody.model;

public class User {
    private String username;
    private String password;
    private Integer balanche;

    public User(String username, String password, Integer balanche) {
        this.username = username;
        this.password = password;
        this.balanche = balanche;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBalanche() {
        return balanche;
    }

    public void setBalanche(Integer balanche) {
        this.balanche = balanche;
    }
}
