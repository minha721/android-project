package com.example.schedulealarm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.CustomViewHolder> {

    private ArrayList<ToDoData> toDoList;
    private ListDBHelper dbHelper;

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView toDoTitle;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            dbHelper = ListDBHelper.getInstance(itemView.getContext());

            this.toDoTitle = (TextView) itemView.findViewById(R.id.toDoTitle);

            toDoTitle.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION) {
                        String tmp = toDoList.get(position).getTitle();
                        dbHelper.delete(tmp);
                        toDoList.remove(position);
                        notifyDataSetChanged();
                    }
                    return true;
                }
            });
        }
    }

    public ToDoAdapter(ArrayList<ToDoData> toDoList) {
        this.toDoList = toDoList;
    }


    public void clear(){
        toDoList.clear();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {
        String tmpTitle = toDoList.get(position).getTitle();
        holder.toDoTitle.setText(tmpTitle);
    }

    @Override
    public int getItemCount() {
        return (null != toDoList ? toDoList.size() : 0);
    }
}
