package com.example.adithyaiyer.planner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiServiceCustomer {
    public static final String ROOT_URL = "http://192.168.1.103:8000/";


    @GET("customers/")
    Call<List<customer>> getMyJSONCustomer();

    @POST("customers/")
    Call<customer> saveCustomer(@Body customer c);

    @GET("customers/{id}/")
    Call<List<task>> getCustomerData(@Path("id") int customerId);

    @POST("tasks/")
    Call<task> saveTask(@Body task t);

    @POST("joiningRecord/")
    Call<joiningTask> saveRelation(@Body joiningTask jk);

    @GET("customers/{id}/lastTask")
    Call<task> getLatest(@Path("id") int customerId);

    @GET("tasks/{id}/")
    Call<task> getTaskData(@Path("id") int taskId);

    @Multipart
    @PUT("tasks/{id}/")
    Call<task> changeTask(@Path("id") int taskId, @Part("taskName") String taskName, @Part("taskDate") String taskDate, @Part("taskTime") String taskTime, @Part("location") String location);
}


