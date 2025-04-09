package com.example.myapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("api/guests")
    Call<Void> saveInputs(@Body List<GuestDetails> inputs);
}
