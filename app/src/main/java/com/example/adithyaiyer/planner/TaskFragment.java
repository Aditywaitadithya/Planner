package com.example.adithyaiyer.planner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class TaskFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private List<task> tester;

    private taskAdapter mAdapter;
    private RecyclerView recyclerView;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public TaskFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TaskFragment newInstance() {
        TaskFragment fragment = new TaskFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_1, container, false);
        return rootView;
        }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);
    }
}

