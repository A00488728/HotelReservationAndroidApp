package com.example.myapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @POST("api/guests")
    Call<Void> saveInputs(@Body List<GuestDetails> inputs);

    @POST("/reservations")
    Call<ReservationData> createReservation(@Body ReservationData reservationData);

    @GET("/reservations/number/{reservationNumber}") // adjust path as per your Spring Boot endpoint
    Call<ReservationData> getReservationByNumber(@Path("reservationNumber") String reservationNumber);
}
