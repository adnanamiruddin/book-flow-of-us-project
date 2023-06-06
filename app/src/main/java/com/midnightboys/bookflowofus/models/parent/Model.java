package com.midnightboys.bookflowofus.models.parent;

public class Model {
    protected int id;

    protected Model(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
