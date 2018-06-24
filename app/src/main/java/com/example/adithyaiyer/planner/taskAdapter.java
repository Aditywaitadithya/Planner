package com.example.adithyaiyer.planner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class taskAdapter extends RecyclerView.Adapter<taskAdapter.CustomViewHolder> {
private List<task> tasks;


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView taskName;
        public CheckBox checkbox;
        public CustomViewHolder(View view) {
            super(view);
            taskName = (TextView) view.findViewById(R.id.taskUnitTextView);
            checkbox=(CheckBox)view.findViewById(R.id.taskCheckbox);

        }

    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.taskitem, parent, false);

        return new CustomViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        task taskposition = tasks.get(position);
        holder.taskName.setText(taskposition.getTaskName().toString());



    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public taskAdapter(List<task> tasks ){
        this.tasks=tasks;
    }



}
