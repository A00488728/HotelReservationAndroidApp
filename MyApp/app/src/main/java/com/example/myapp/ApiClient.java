package com.example.myapp;

import retrofit2.Retrofit;  // Importing Retrofit class to make API requests
import retrofit2.converter.gson.GsonConverterFactory;  // Importing GsonConverterFactory to convert JSON responses into Java objects

// ApiClient class to configure and provide a Retrofit instance for API communication
public class ApiClient {

    // Base URLs for the API endpoints (one for production and one for local development)
    private static final String BASE_URL = "https://lit-river-27245-589367aa3742.herokuapp.com/";  // URL for the deployed API
    private static final String BASE_URL2 = "http://10.0.2.2:8080/"; // Localhost URL for development/testing (use 10.0.2.2 for Android emulator)

    // Static Retrofit instance to avoid recreating it multiple times
    private static Retrofit retrofit;

    // Method to get the Retrofit instance (creates it if not already created)
    public static Retrofit getRetrofitInstance() {
        // If Retrofit instance doesn't exist, create a new one
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL2)  // Sets the base URL for the API requests
                    .addConverterFactory(GsonConverterFactory.create())  // Adds a converter to parse the JSON responses into Java objects using Gson
                    .build();  // Builds and returns the Retrofit instance
        }
        // Returns the Retrofit instance
        return retrofit;
    }
}
