package com.developersoffxinnovate.bookflowofus.models;

public class Admin extends Model {
    private int id;
    private String user;
    private String password;

    public Admin(int id, String user, String password) {
        super(id);
        this.user = user;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
