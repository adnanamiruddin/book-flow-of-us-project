package com.midnightboys.bookflowofus.models;

import com.midnightboys.bookflowofus.models.parent.Model;

public class Admin extends Model {
    private String user;

    public Admin(int id, String user) {
        super(id);
        this.user = user;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
}
