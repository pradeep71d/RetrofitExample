package com.example.retrofitp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Retrofit retrofit=null;
    public static ApiInterface getclient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl("https://mobileappdatabase.in/").addConverterFactory(GsonConverterFactory.create()).build();
        }
            ApiInterface api = retrofit.create(ApiInterface.class);
            return api;
        }

}
