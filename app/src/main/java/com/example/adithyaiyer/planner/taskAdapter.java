package com.example.adithyaiyer.planner;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class taskAdapter extends RecyclerView.Adapter<taskAdapter.CustomViewHolder> {
private List<task> tasks;
private Context context;




    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView taskName;
        public CheckBox checkbox;
        public LinearLayout itemLay;
        public ImageButton imageButton;
        public CustomViewHolder(View view) {
            super(view);
           // context=view.getContext();
            taskName = (TextView) view.findViewById(R.id.taskUnitTextView);
            checkbox=(CheckBox)view.findViewById(R.id.taskCheckbox);
            itemLay=(LinearLayout)view.findViewById(R.id.itemLayout);
            imageButton=(ImageButton)view.findViewById(R.id.imageButton);
        }

    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.taskitem, parent, false);
      //  context=itemView.getContext();
        return new CustomViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        task taskposition = tasks.get(position);
        final int k=tasks.get(position).getId();
        holder.taskName.setText(taskposition.getTaskName().toString());
        holder.taskName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, EditTask.class);
                i.putExtra("pkvalue",tasks.get(position).getId());
                context.startActivity(i);

            }
        });
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog alertDialog = new AlertDialog.Builder(
                        context).create();
                alertDialog.setMessage("Detete");
                alertDialog.setMessage("Are you sure you want to delete this task?");
                alertDialog.setButton(Dialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(ApiServiceCustomer.ROOT_URL)
                                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                                .build();
                        ApiServiceCustomer api= retrofit.create(ApiServiceCustomer.class);
                        Call<task> call2=api.deleteTask(k);

                     call2.enqueue(new Callback<task>() {
                            @Override
                            public void onResponse(Call<task> call, Response<task> response) {
                                Toast.makeText(context,"task deleted",Toast.LENGTH_SHORT);
                           //     context.startActivity(new Intent(context,Main_task.class));
                            }

                            @Override
                            public void onFailure(Call<task> call, Throwable t) {
                                Toast.makeText(context,"check internet",Toast.LENGTH_SHORT);
                            }
                        });
                    }
                });
                alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });


    }



    @Override
    public int getItemCount() {
        if(tasks==null){return 0;}
        return tasks.size();
    }

    public taskAdapter(List<task> tasks,Context context ){
        this.tasks=tasks;
        this.context=context;
    }




}


