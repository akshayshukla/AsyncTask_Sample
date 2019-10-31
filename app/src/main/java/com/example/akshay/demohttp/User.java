package com.example.akshay.demohttp;

/**
 * Created by Akshay on 24-04-2017.
 */

public class User {

    public int icon;
    public String name;
    public String email;
    public String usergroup_title;

    public User() {
        super();
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsergroup_title() {
        return usergroup_title;
    }

    public void setUsergroup_title(String usergroup_title) {
        this.usergroup_title = usergroup_title;
    }

    public int getIcon() {
        return icon;
    }
}