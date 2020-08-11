package com.example.schedulealarm;

public class ListDBContract {
    private ListDBContract(){}

    public static final String TABLE_NAME = "todo";
    public static final String COL_LIST_NAME = "todo_title";
    public static final String COL_YEAR = "todo_year";
    public static final String COL_MONTH = "todo_month";
    public static final String COL_DATE = "todo_date";

    public static final String SQL_CREATE_TBL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            COL_LIST_NAME + " CHAR(20) PRIMARY KEY, " + COL_YEAR + " INT, " + COL_MONTH + " INT, " + COL_DATE + " INT)";
    public static final String SQL_DROP_TBL = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String SQL_SELECT = "SELECT " + COL_LIST_NAME + " FROM " + TABLE_NAME + " WHERE ";
    public static final String SQL_SELECT_ALL = "SELECT * FROM " + TABLE_NAME;
    public static final String SQL_INSERT = "INSERT OR REPLACE INTO " + TABLE_NAME + " (" + COL_LIST_NAME + ", " + COL_YEAR + ", " + COL_MONTH + ", " + COL_DATE + ") VALUES ";
    public static final String SQL_DELETE = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_LIST_NAME + " = ";
}