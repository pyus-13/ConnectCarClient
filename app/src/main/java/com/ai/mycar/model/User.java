package com.ai.mycar.model;

/**
 * Created by Piyush Agarwal on 1/7/17.
 */
public class User {

    private String name;

    private String password;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
