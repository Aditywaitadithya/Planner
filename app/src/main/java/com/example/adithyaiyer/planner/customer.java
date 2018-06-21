package com.example.adithyaiyer.planner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class customer {

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("email_id")
    @Expose
    private String email_id;

    public customer(String username,String password, String email_id){
        this.username=username;
        this.password=password;
        this.email_id=email_id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail_id() {
        return email_id;
    }


    public String getUsername() {
        return username;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String toString(){
        return "customer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email_id=" + email_id  +
                '}';
    }

}
