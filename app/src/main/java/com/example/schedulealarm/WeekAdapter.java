package com.example.schedulealarm;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.CustomViewHolder> {

    private ArrayList<WeekData> weekList;
    public ListDBHelper listDBHelper;
    public ArrayList<ToDoData> toDoList;
    public ToDoAdapter toDoAdapter;
    private int selectedPosition = -1;

    public WeekAdapter(ArrayList<WeekData> weekList,
                       ArrayList<ToDoData> toDoList,
                       ToDoAdapter toDoAdapter
    ) {
        this.weekList = weekList;
        this.toDoList = toDoList;
        this.toDoAdapter = toDoAdapter;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.week_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        String month = Integer.toString(weekList.get(position).getMonth());
        String day = Integer.toString(weekList.get(position).getDay());
        String week = weekList.get(position).getWeek();

        holder.month.setText(month);
        holder.day.setText(day);
        holder.week.setText(week);

        holder.itemView.setTag(position);

        if(selectedPosition == position) {
            holder.itemBack.setBackgroundColor(Color.rgb(196, 189,187));
        } else {
            holder.itemBack.setBackgroundColor(Color.rgb(240, 229,222));
        }
    }

    @Override
    public int getItemCount() {
        return (null != weekList ? weekList.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected LinearLayout itemBack;
        protected TextView month;
        protected TextView day;
        protected TextView week;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.month = (TextView) itemView.findViewById(R.id.month);
            this.day = (TextView) itemView.findViewById(R.id.day);
            this.week = (TextView) itemView.findViewById(R.id.week);
            this.itemBack = (LinearLayout) itemView.findViewById(R.id.itemBack);

            itemClick(itemView);
        }

        private void itemClick(final View view) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int year = 2020;
                    int month = weekList.get(getAdapterPosition()).getMonth();
                    int date = weekList.get(getAdapterPosition()).getDay();

                    listDBHelper = ListDBHelper.getInstance(view.getContext());
                    ArrayList<ToDoData> tempList = listDBHelper.select(year, month, date);

                    toDoAdapter.clear();
                    toDoAdapter.notifyDataSetChanged();

                    for(int i = 0; i < tempList.size(); i++) {
                        toDoList.add(tempList.get(i));
                        toDoAdapter.notifyDataSetChanged();
                    }

                    selectPosition();
                }
            });
        }

        private void selectPosition() {
            selectedPosition = getAdapterPosition();
            notifyDataSetChanged();
        }
    }
}
