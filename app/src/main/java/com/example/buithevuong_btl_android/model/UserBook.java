package com.example.buithevuong_btl_android.model;

public class UserBook {
    private int id;
    private int viewed;
    private float voted;
    private int viewlate;
    private int userId;
    private int bookId;


    public UserBook() {
    }

    public UserBook(int id, int viewed, float voted, int viewlate, int userId, int bookId) {
        this.id = id;
        this.viewed = viewed;
        this.voted = voted;
        this.viewlate = viewlate;
        this.userId = userId;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    public float getVoted() {
        return voted;
    }

    public void setVoted(float voted) {
        this.voted = voted;
    }

    public int getViewlate() {
        return viewlate;
    }

    public void setViewlate(int viewlate) {
        this.viewlate = viewlate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
