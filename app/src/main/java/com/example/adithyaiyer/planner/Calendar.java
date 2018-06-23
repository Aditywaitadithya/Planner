package com.example.adithyaiyer.planner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Calendar extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public Calendar() {
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
}

