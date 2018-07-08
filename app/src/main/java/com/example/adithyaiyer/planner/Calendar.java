package com.example.adithyaiyer.planner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Calendar extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private TextView t;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView recyclerView;
    private taskAdapter mAdapter;
    private ArrayList<task> listNeeded;
    private Button b;
    private String date;
    public Calendar() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Calendar newInstance() {
        Calendar fragment = new Calendar();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_2, container, false);
        return rootView;


    }


    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CalendarView calendarView=(CalendarView)view.findViewById(R.id.calendarView);
        Long l=calendarView.getDate();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {



            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {



                String month;
              String day;
               int ii=i1+1;
               if(ii<10){
                   month=String.valueOf(0)+String.valueOf(ii);
               }
               else{
                   month=String.valueOf(ii);
               }
                final Main_task activity = (Main_task) getActivity();
                if(i2<10){
                    day=String.valueOf(0)+String.valueOf(i2);
                }
                else{
                    day=String.valueOf(i2);
                }
                date = i + "-" + month + "-"+ day ;
                b=(Button)view.findViewById(R.id.addTaskButtonNaya) ;
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent go=new Intent(activity.getApplicationContext(),AddTask.class);
                        go.putExtra("dateOfTask",date);
                        startActivity(go);
                    }
                });
                Toast.makeText(getActivity(),
                        date, Toast.LENGTH_SHORT).show();

                List<task> listyReceived=activity.getListOfTasks();
                listNeeded=new ArrayList<task>();

                 for(int j=0;j<listyReceived.size();j++){
                     if(listyReceived.get(j).getTaskDate().toString().equals(date)){
                         listNeeded.add(listyReceived.get(j));
                     }
                 }
             //   int k=listNeeded.size();


                recyclerView=(RecyclerView)getView().findViewById(R.id.recycleCalendar);
                mAdapter = new taskAdapter(listNeeded);
                RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(activity.getApplicationContext());
                recyclerView.setLayoutManager(eLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);

                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(activity.getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {
                                // do whatever
                                Main_task activity = (Main_task) getActivity();
                                int idOfTask = listNeeded.get(position).getId();
                                Intent go=new Intent(activity.getApplicationContext(),EditTask.class);
                                go.putExtra("pkvalue",idOfTask);
                                startActivity(go);
                            }

                            @Override public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
                );


            }


        });
    }
    public void addOnThisDate(View view){



    }
}

