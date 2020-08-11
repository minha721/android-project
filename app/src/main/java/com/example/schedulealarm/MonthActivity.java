package com.example.schedulealarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Calendar;

public class MonthActivity extends AppCompatActivity {
    private ArrayList<WeekData> weekList;
    private ListDBHelper dbHelper;

    public ListDBHelper listDBHelper;
    private MonthAdapter monthToDoAdapter;
    private ArrayList<MonthData> toDoList;
    private int selectedPosition = -1;

    ImageButton btnAdd, btnWeek, btnTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        dbHelper = ListDBHelper.getInstance(getApplicationContext());

        RecyclerView recyclerToDo = (RecyclerView) findViewById(R.id.monthToDoList);
        LinearLayoutManager toDoLinearLayoutManager = new LinearLayoutManager(this);
        recyclerToDo.setLayoutManager(toDoLinearLayoutManager);

        toDoList = new ArrayList<>();
        monthToDoAdapter = new MonthAdapter(toDoList);
        recyclerToDo.setAdapter(monthToDoAdapter);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        listDBHelper = ListDBHelper.getInstance(getApplicationContext());
        ArrayList<MonthData> tempList = listDBHelper.selectMonth(cYear, cMonth + 1, cDay);

        monthToDoAdapter.clear();

        monthToDoAdapter.notifyDataSetChanged();

        for(int i = 0; i < tempList.size(); i++) {
            toDoList.add(tempList.get(i));
            monthToDoAdapter.notifyDataSetChanged();
        }

        CalendarView calendar = (CalendarView)findViewById(R.id.calendar);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int monthOfYear, int dayOfMonth) {
                listDBHelper = ListDBHelper.getInstance(calendarView.getContext());
                ArrayList<MonthData> tempList = listDBHelper.selectMonth(year, monthOfYear + 1, dayOfMonth);

                monthToDoAdapter.clear();

                monthToDoAdapter.notifyDataSetChanged();

                for(int i = 0; i < tempList.size(); i++) {
                    toDoList.add(tempList.get(i));
                    monthToDoAdapter.notifyDataSetChanged();
                }
            }
        });

        btnAdd = (ImageButton)findViewById(R.id.btnAdd);
        final Intent intentAdd = new Intent(this, AddActivity.class);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentAdd);
            }
        });

        btnWeek = (ImageButton)findViewById(R.id.btnWeek);
        final Intent intentWeek = new Intent(this, MainActivity.class);
        btnWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentWeek);
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
}
