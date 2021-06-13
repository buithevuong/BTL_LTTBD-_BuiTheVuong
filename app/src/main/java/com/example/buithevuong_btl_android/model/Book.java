package com.example.buithevuong_btl_android.model;

import java.io.Serializable;


public class Book implements Serializable {

    private Integer id;
    private String image;
    private String title;
    private String description;
    private String content;
    private float rate;
    private int amountRate;
    public Book() {
    }

    public Book(Integer id, String image, String title, String description, String content, float rate) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.content = content;
        this.rate = rate;
    }

    public Book(String image, String title, String description, String content, float rate) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.content = content;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
