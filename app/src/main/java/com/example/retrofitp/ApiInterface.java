package com.example.retrofitp;


import retrofit2.Call;

import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("/retrofit/register.php")
    Call<SignUpResponse> registration(@Query("name") String name, @Query("email") String email, @Query("password") String password, @Query("logintype") String logintype);
}