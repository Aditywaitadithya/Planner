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

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("customersInvolved")
    @Expose
    private List<Integer> customersInvolved;

    public List<Integer> getCustomersInvolved() {
        return customersInvolved;
    }

    public void setCustomersInvolved(List<Integer> customersInvolved) {
        this.customersInvolved = customersInvolved;
    }

    public task(String taskName, String taskDate, String taskTime, String location){
        this.taskName=taskName;
        this.taskDate=taskDate;
        this.taskTime=taskTime;
        this.location=location;



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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String toString(){
        return "task{" +
                "taskName='" + taskName + '\'' +
                ", taskDate='" + taskDate + '\'' +
                ", taskTime=" + taskTime  + ", location="+ location+
                '}';
    }

}
