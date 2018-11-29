package com.bookstore.domain;

/**
 * Created by lenovo on 2018/11/21.
 */
public class Trolley {

    private int trolleyId;
    private int userId;
    private int bookId;
    private int number;

    public int getTrolleyId() {
        return trolleyId;
    }

    public void setTrolleyId(int trolleyId) {
        this.trolleyId = trolleyId;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


}
