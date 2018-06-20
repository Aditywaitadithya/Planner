package com.example.adithyaiyer.planner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class customerList {

    @SerializedName("customer")
    @Expose
    private ArrayList<customer> customer=null;

    public ArrayList<customer> getCustomer() {
        return customer;
    }

    public void setCustomer(ArrayList<customer> customer) {
        this.customer = customer;
    }

}
