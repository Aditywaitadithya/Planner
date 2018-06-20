package com.example.adithyaiyer.planner;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClientCustomer {
    private static final String ROOT_URL = "https://demo1340301.mockable.io/";

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static ApiServiceCustomer getApiServiceCustomer() {
        return getRetrofitInstance().create(ApiServiceCustomer.class);
    }

}
