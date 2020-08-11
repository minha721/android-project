package com.example.schedulealarm;

public class WeekData {

    private int month;
        private int day;
        private String week;

        public WeekData(int month, int day, String week) {
            this.month = month;
            this.day = day;
        this.week = week;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getWeek() {
        return week;
    }
}