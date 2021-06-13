package com.example.buithevuong_btl_android.model;

import java.io.Serializable;

public class Category implements Serializable {

    private int id;

    private String title;

    private int image;

    public Category() {
    }

    public Category(int id, String title, int image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
