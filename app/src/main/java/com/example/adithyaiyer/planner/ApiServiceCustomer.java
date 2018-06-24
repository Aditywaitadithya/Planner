package com.example.adithyaiyer.planner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServiceCustomer {
    public static final String ROOT_URL = "http://192.168.1.102:8000/";



    @GET("customers/")
    Call<List<customer>> getMyJSONCustomer();

    @POST("customers/")
    Call<customer> saveCustomer(@Body customer c);

    @GET("customers/{id}/")
    Call<List<task>> getCustomerData(@Path("id") int customerId);



}


