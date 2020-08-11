package com.example.schedulealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    EditText editTitle;
    ImageButton btnWeek, btnMonth, btnSetDate, btnTime;
    DatePicker setDate;
    Button btnReset,btnOK;

    int year, month, date;

    private ListDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dbHelper = ListDBHelper.getInstance(getApplicationContext());

        btnSetDate = (ImageButton)findViewById(R.id.btnSetDate);
        setDate = (DatePicker)findViewById(R.id.setDate);
        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate.setVisibility(View.VISIBLE);
            }
        });

        editTitle = (EditText)findViewById(R.id.editTitle);
        btnReset = (Button)findViewById(R.id.btnReset);
        btnOK = (Button)findViewById(R.id.btnOK);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToDoData todo = new ToDoData(editTitle.getText().toString(), setDate.getYear(), setDate.getMonth(), setDate.getDayOfMonth());
                dbHelper.insert(todo);

                editTitle.clearFocus();
                setDate.clearFocus();

                editTitle.setText(null);
                setToday();

                Toast.makeText(getApplicationContext(),"저장 완료", Toast.LENGTH_SHORT).show();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTitle.setText(null);
                setToday();
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

    private void setToday(){
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        date = c.get(Calendar.DAY_OF_MONTH);

        setDate.updateDate(year, month, date);
    }
}
