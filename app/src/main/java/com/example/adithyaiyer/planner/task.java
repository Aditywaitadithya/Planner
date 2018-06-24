package com.example.adithyaiyer.planner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class task {

    @SerializedName("taskName")
    @Expose
    private String taskName;

    @SerializedName("taskDate")
    @Expose
    private String taskDate;

    @SerializedName("taskTime")
    @Expose
    private String taskTime;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("customerInvolved")
    @Expose
    private List<Integer> customersInvolved;

    public void setCustomersInvolved(List<Integer> customersInvolved) {
        this.customersInvolved = customersInvolved;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public List<Integer> getCustomersInvolved() {
        return customersInvolved;
    }

    public String getLocation() {
        return location;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskTime() {
        return taskTime;
    }
}
