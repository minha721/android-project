package com.example.schedulealarm;

public class ToDoData {

    private String title;
    private int year;
    private int month;
    private int date;

    public ToDoData(
            String title,
            int year,
            int month,
            int date) {
        this.title = title;
        this.year = year;
        this.month = month+1;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }
}
