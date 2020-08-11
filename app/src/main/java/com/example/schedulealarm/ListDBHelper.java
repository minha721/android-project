package com.example.schedulealarm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.Month;
import java.util.ArrayList;

public class ListDBHelper extends SQLiteOpenHelper {

    private static volatile ListDBHelper instance;

    private static final String DB_NAME = "list_db";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase db = null;

    private ListDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        if(db == null) {
            db = getWritableDatabase();
        }
    }

    public static ListDBHelper getInstance(Context context){
        if(instance == null){
            synchronized (ListDBHelper.class){
                if(instance == null)
                    instance = new ListDBHelper(context);
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ListDBContract.SQL_CREATE_TBL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(ListDBContract.SQL_DROP_TBL);
        onCreate(db);
    }

    public void insert(ToDoData todo){
        db = getWritableDatabase();
        db.execSQL(ListDBContract.SQL_INSERT + "(" + '"' + todo.getTitle() + '"' + ", " + todo.getYear() + ", " +
                todo.getMonth() + ", " + todo.getDate() + ")");
    }

    public void delete(String title){
        db = getWritableDatabase();
        db.execSQL(ListDBContract.SQL_DELETE + '"' + title + '"');
    }

    public ArrayList<ToDoData> select(int year, int month, int date){
        ArrayList<ToDoData> tmp = new ArrayList<>();
        db = getReadableDatabase();

        String sqlSelect = ListDBContract.SQL_SELECT + ListDBContract.COL_YEAR + " = " + year + " and " + ListDBContract.COL_MONTH + " = " + month + " and " + ListDBContract.COL_DATE + " = " + date;

        Cursor cursor = null;

        cursor = db.rawQuery(sqlSelect, null);

        while(cursor.moveToNext()) {
            String txtList = cursor.getString(0);

            ToDoData data = new ToDoData(txtList, year, month, date);
            tmp.add(data);
        }

        return tmp;
    }

    public ArrayList<ToDoData> getAll(){
        ArrayList<ToDoData> tmp = new ArrayList<>();
        db=getReadableDatabase();

        Cursor cursor;
        cursor = db.rawQuery(ListDBContract.SQL_SELECT_ALL, null);

        while(cursor.moveToNext()){
            String txtList = cursor.getString(0);
            int todoYear = Integer.parseInt(cursor.getString(1));
            int todoMonth = Integer.parseInt(cursor.getString(2));
            int todoDate = Integer.parseInt(cursor.getString(3));

            ToDoData data = new ToDoData(txtList, todoYear, todoMonth, todoDate);
            tmp.add(data);
        }
        return tmp;
    }

    public ArrayList<MonthData> selectMonth(int year, int month, int date){
        ArrayList<MonthData> tmp = new ArrayList<>();
        db = getReadableDatabase();

        String sqlSelect = ListDBContract.SQL_SELECT + ListDBContract.COL_YEAR + " = " + year + " and " + ListDBContract.COL_MONTH + " = " + month + " and " + ListDBContract.COL_DATE + " = " + date;

        Cursor cursor = null;

        cursor = db.rawQuery(sqlSelect, null);

        while(cursor.moveToNext()) {
            String txtList = cursor.getString(0);

            MonthData data = new MonthData(txtList, year, month, date);
            tmp.add(data);
        }

        return tmp;
    }

    public ArrayList<MonthData> getAllMonth(){
        ArrayList<MonthData> tmp = new ArrayList<>();
        db=getReadableDatabase();

        Cursor cursor;
        cursor = db.rawQuery(ListDBContract.SQL_SELECT_ALL, null);

        while(cursor.moveToNext()){
            String txtList = cursor.getString(0);
            int todoYear = Integer.parseInt(cursor.getString(1));
            int todoMonth = Integer.parseInt(cursor.getString(2));
            int todoDate = Integer.parseInt(cursor.getString(3));

            MonthData data = new MonthData(txtList, todoYear, todoMonth, todoDate);
            tmp.add(data);
        }
        return tmp;
    }
}