package com.example.adithyaiyer.planner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class joiningTask {

    @SerializedName("customer")
    @Expose
    private Integer customer;

    @SerializedName("taskDetails")
    @Expose
    private Integer taskDetails;

    @SerializedName("dateOfJoining")
    @Expose
    private String dateOfJoining;

    public joiningTask(int i, int j, String s){
        this.customer=i;this.dateOfJoining=s;this.taskDetails=j;
    }

    public Integer getCustomer() {
        return customer;
    }

    public Integer getTaskDetails() {
        return taskDetails;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public void setTaskDetails(Integer taskDetails) {
        this.taskDetails = taskDetails;
    }
}
