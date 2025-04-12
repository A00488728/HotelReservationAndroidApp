package com.example.myapp;

import java.util.List;

import retrofit2.Call;  // Importing Retrofit's Call class for handling API requests
import retrofit2.http.Body;  // Importing Body annotation to indicate the body of the HTTP request
import retrofit2.http.GET;  // Importing GET annotation for making GET requests
import retrofit2.http.POST;  // Importing POST annotation for making POST requests
import retrofit2.http.Path;  // Importing Path annotation to pass parameters in the URL path

// Define the API interface for interacting with the server-side endpoints
public interface ApiInterface {

    // POST request to save guest details
    @POST("api/guests")  // Defines the endpoint for saving guest details in the database
    Call<Void> saveInputs(@Body List<GuestDetails> inputs);  // Takes a list of GuestDetails objects as input

    // POST request to create a reservation
    @POST("/reservations")  // Defines the endpoint for creating a reservation
    Call<ReservationData> createReservation(@Body ReservationData reservationData);  // Takes a ReservationData object as input and returns ReservationData in the response

    // GET request to retrieve reservation data by reservation number
    @GET("/reservations/number/{reservationNumber}")  // Defines the endpoint for retrieving reservation details by the reservation number
    Call<ReservationData> getReservationByNumber(@Path("reservationNumber") String reservationNumber);  // The reservation number is passed as a path parameter
}
