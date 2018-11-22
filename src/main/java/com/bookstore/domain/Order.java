package com.bookstore.domain;

public class Order{
    private int id;
    private int userid;
    private String books;
    private int allmoney;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public int getAllmoney() {
        return allmoney;
    }

    public void setAllmoney(int allmoney) {
        this.allmoney = allmoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}