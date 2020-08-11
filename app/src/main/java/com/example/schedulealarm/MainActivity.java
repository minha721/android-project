package com.example.schedulealarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ToDoAdapter toDoAdapter;
    private ArrayList<ToDoData> toDoList;

    private ArrayList<WeekData> weekList;
    private WeekAdapter weekAdapter;

    private ImageButton btnAdd, btnMonth, btnTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerToDo = (RecyclerView) findViewById(R.id.toDoList);
        LinearLayoutManager toDoLinearLayoutManager = new LinearLayoutManager(this);
        recyclerToDo.setLayoutManager(toDoLinearLayoutManager);

        toDoList = new ArrayList<>();
        toDoAdapter = new ToDoAdapter(toDoList);
        recyclerToDo.setAdapter(toDoAdapter);

        RecyclerView recyclerWeek = (RecyclerView) findViewById(R.id.weekView);
        LinearLayoutManager weekLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerWeek.setLayoutManager(weekLinearLayoutManager);

        weekList = new ArrayList<>();
        weekAdapter = new WeekAdapter(weekList, toDoList, toDoAdapter);
        recyclerWeek.setAdapter(weekAdapter);

        setWeekData();

        btnAdd = (ImageButton)findViewById(R.id.btnAdd);
        final Intent intentAdd = new Intent(this, AddActivity.class);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentAdd);
            }
        });

        btnMonth = (ImageButton)findViewById(R.id.btnMonth);
        final Intent intentMonth = new Intent(this, MonthActivity.class);
        btnMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentMonth);
            }
        });

        btnTime = (ImageButton)findViewById(R.id.btnTime);
        final Intent intentTime = new Intent(this, SettingActivity.class);
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentTime);
            }
        });
    }

    private void setWeekData() {
        Calendar c1 = Calendar.getInstance();

        c1.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        int month1 = c1.get(Calendar.MONTH)+1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);

        c1.add(Calendar.DAY_OF_WEEK, 1);
        int month2 = c1.get(Calendar.MONTH)+1;
        int day2 = c1.get(Calendar.DAY_OF_MONTH);

        c1.add(Calendar.DAY_OF_WEEK, 1);
        int month3 = c1.get(Calendar.MONTH)+1;
        int day3 = c1.get(Calendar.DAY_OF_MONTH);

        c1.add(Calendar.DAY_OF_WEEK, 1);
        int month4 = c1.get(Calendar.MONTH)+1;
        int day4 = c1.get(Calendar.DAY_OF_MONTH);

        c1.add(Calendar.DAY_OF_WEEK, 1);
        int month5 = c1.get(Calendar.MONTH)+1;
        int day5 = c1.get(Calendar.DAY_OF_MONTH);

        c1.add(Calendar.DAY_OF_WEEK, 1);
        int month6 = c1.get(Calendar.MONTH)+1;
        int day6 = c1.get(Calendar.DAY_OF_MONTH);

        c1.add(Calendar.DAY_OF_WEEK, 1);
        int month7 = c1.get(Calendar.MONTH)+1;
        int day7 = c1.get(Calendar.DAY_OF_MONTH);

        WeekData weekData1 = new WeekData(month1, day1, "Sunday");
        weekList.add(weekData1);
        WeekData weekData2 = new WeekData(month2, day2, "Monday");
        weekList.add(weekData2);
        WeekData weekData3 = new WeekData(month3, day3, "Tuesday");
        weekList.add(weekData3);
        WeekData weekData4 = new WeekData(month4, day4, "Wednesday");
        weekList.add(weekData4);
        WeekData weekData5 = new WeekData(month5, day5, "Thursday");
        weekList.add(weekData5);
        WeekData weekData6 = new WeekData(month6, day6, "Friday");
        weekList.add(weekData6);
        WeekData weekData7 = new WeekData(month7, day7, "Saturday");
        weekList.add(weekData7);

        weekAdapter.notifyDataSetChanged();
    }
}
