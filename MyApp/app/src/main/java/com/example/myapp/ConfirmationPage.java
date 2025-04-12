package com.example.myapp;

import android.content.Intent;  // Importing Intent class for activity navigation
import android.os.Bundle;  // Importing Bundle for passing data between activities
import android.util.Log;  // Importing Log for logging debug/error messages
import android.view.View;  // Importing View class for handling UI elements
import android.widget.Button;  // Importing Button class for handling button clicks
import android.widget.TextView;  // Importing TextView for displaying text in UI

import androidx.activity.EdgeToEdge;  // Importing EdgeToEdge for enabling edge-to-edge display
import androidx.appcompat.app.AppCompatActivity;  // Importing AppCompatActivity for compatibility
import androidx.core.graphics.Insets;  // Importing Insets for managing window insets
import androidx.core.view.ViewCompat;  // Importing ViewCompat for accessing window insets
import androidx.core.view.WindowInsetsCompat;  // Importing WindowInsetsCompat for managing insets

import retrofit2.Call;  // Importing Retrofit Call class for API requests
import retrofit2.Callback;  // Importing Retrofit Callback class to handle responses
import retrofit2.Response;  // Importing Retrofit Response class for handling server responses

public class ConfirmationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  // Calling superclass's onCreate method
        EdgeToEdge.enable(this);  // Enabling edge-to-edge display mode for a fullscreen experience
        setContentView(R.layout.activity_confirmation_page);  // Setting the content view for this activity

        // Retrieve the reservation number passed from the previous activity
        Intent intent = getIntent();
        String reservationNumber = intent.getStringExtra("reservationNumber");

        // Find the TextView where the confirmation number will be displayed
        TextView confirmationNumberTextView = findViewById(R.id.confirmationNumberTextView);

        // Creating an instance of the API service to make the network request
        ApiInterface apiService = ApiClient.getRetrofitInstance().create(ApiInterface.class);

        // Creating a Retrofit call to get reservation data using the reservation number
        Call<ReservationData> call = apiService.getReservationByNumber(reservationNumber);

        // Enqueue the call to be executed asynchronously
        call.enqueue(new Callback<ReservationData>() {
            @Override
            public void onResponse(Call<ReservationData> call, Response<ReservationData> response) {
                if (response.isSuccessful()) {
                    // If the response is successful, retrieve the reservation data
                    ReservationData user = response.body();
                    String confirmationNumber = user.getConfirmationNumber();

                    // Set the confirmation number to the TextView
                    confirmationNumberTextView.setText("Confirmation Number: " + confirmationNumber);

                    // Log the confirmation number for debugging purposes
                    Log.d("API", "Name: " + user.getConfirmationNumber());
                } else {
                    // If the response failed, display a failure message in the TextView
                    confirmationNumberTextView.setText("Failed to retrieve confirmation number.");
                    // Log the error with the response code
                    Log.e("API", "Response failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ReservationData> call, Throwable t) {
                // If the network request fails, log the error message
                Log.e("API", "Request failed: " + t.getMessage());
            }
        });

        // Setting up a button to return to the main activity when clicked
        Button returnBtn = findViewById(R.id.Return);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating an Intent to navigate back to the MainActivity
                Intent returnToTheMain = new Intent(ConfirmationPage.this, MainActivity.class);
                // Starting the MainActivity
                startActivity(returnToTheMain);
            }
        });
    }
}
