package com.midnightboys.bookflowofus.models;

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
