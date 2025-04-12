package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.security.SecureRandom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.ApiClient;
import com.example.myapp.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestList extends AppCompatActivity {

    private RecyclerView recyclerView;  // RecyclerView to display the list of guests
    private MyRecyclerViewAdapter adapter;  // Adapter for the RecyclerView
    private List<GuestDetails> itemList;  // List to store guest details
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";  // Characters for reservation code generation
    private static final int CODE_LENGTH = 10;  // Length of reservation code
    private static final SecureRandom random = new SecureRandom();  // SecureRandom for generating random reservation code

    private String reservationNumber;  // Reservation number for the booking

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);  // Set the layout for the activity

        Intent intent = getIntent();
        String cityReceived = intent.getStringExtra("city_received");
        int roomsReceived = intent.getIntExtra("rooms_received", 0);
        int guestsReceived = intent.getIntExtra("guests_received", 0);
        String checkindate = intent.getStringExtra("startDate");
        String checkoutDate = intent.getStringExtra("endDate");
        Log.d("IntentReceived", "City Received: " + cityReceived);
        Log.d("IntentReceived", "Rooms Received: " + roomsReceived);
        Log.d("IntentReceived", "Guests Received: " + guestsReceived);
        Hotel selectedHotel = (Hotel) intent.getSerializableExtra("selected_hotel");  // Retrieve selected hotel object

        String AddString ="Hotel Name: ";
        TextView HotelName = findViewById(R.id.name);
        HotelName.setText(AddString + selectedHotel.getName());

        AddString = "Check in date: ";
        TextView CheckinDate = findViewById(R.id.checkinDate);
        CheckinDate.setText(AddString + checkindate);

        AddString = "Check out date: ";
        TextView CheckoutDate = findViewById(R.id.checkoutDate);
        CheckoutDate.setText(AddString + checkoutDate);

        AddString ="Hotel Price: ";
        TextView HotelPrice = findViewById(R.id.price);
        HotelPrice.setText(AddString + selectedHotel.getPrice());

        recyclerView = findViewById(R.id.recyclerView);  // Initialize RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  // Set layout manager for RecyclerView

        // Generate a random reservation number
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());  // Pick random index from CHARACTERS string
            sb.append(CHARACTERS.charAt(index));  // Append character to the reservation number
        }

        reservationNumber = sb.toString();  // Assign the generated reservation number

        // Retrieve intent data from the previous activity


        // Create ReservationData object to store reservation details
        ReservationData reservation_data = new ReservationData("", guestsReceived, selectedHotel.getName(), checkoutDate, checkindate, reservationNumber, roomsReceived, cityReceived);

        // Initialize itemList with GuestDetails objects (empty for now)
        itemList = new ArrayList<>();
        for (int i = 0; i < guestsReceived; i++) {
            // Add a guest with no name, unselected gender, and reservation number
            itemList.add(new GuestDetails("", false, false, reservationNumber));  // All RadioButtons initially unselected
        }

        // Set up the RecyclerView adapter
        adapter = new MyRecyclerViewAdapter(itemList);
        recyclerView.setAdapter(adapter);  // Attach the adapter to the RecyclerView

        // Log RecyclerView height and scroll range (for debugging purposes)
        recyclerView.post(() -> {
            Log.d("RecyclerView", "Total height: " + recyclerView.getHeight());
            Log.d("RecyclerView", "Scroll range: " + recyclerView.computeVerticalScrollRange());
        });

        // Set up the Submit button click listener
        Button submitbtn = findViewById(R.id.submit);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("GuestList", "Submit button clicked");
                int validate_done = 0;  // Validation flag

                // Get guest details from the adapter
                List<GuestDetails> inputs = adapter.getGuestDetails();

                Log.d("GuestList", "Guest details retrieved: " + inputs.size() + " items");

                // For debugging: Show the first and last names (if needed)
                if (!inputs.isEmpty()) {
                    Log.d("GuestList", "First guest name: " + inputs.get(0).getName());
                    Log.d("GuestList", "Last guest name: " + inputs.get(inputs.size() - 1).getName());
                }

                // Validate each guest's input
                for (GuestDetails guest : inputs) {
                    // Check if the name is invalid (empty or contains invalid characters)
                    if (guest.getName() == null || !guest.getName().matches("[a-zA-Z ]+") || guest.getName().trim().length() < 1) {
                        Toast.makeText(GuestList.this, "Invalid guest name", Toast.LENGTH_LONG).show();
                        validate_done = 0;  // Set validation flag to 0 if validation fails
                        break;
                    } else if (!guest.getFemale() && !guest.getMale()) {  // Check if gender is not selected
                        Toast.makeText(GuestList.this, "Guest gender not entered", Toast.LENGTH_LONG).show();
                        validate_done = 0;
                        break;
                    } else {
                        validate_done = 1;  // Validation passed
                    }
                }

                // If validation is successful, save the guest details and send the reservation data to the backend
                if (validate_done == 1) {
                    saveGuestDetails(inputs);  // Save guest details
                    sendUserToBackend(reservation_data);  // Send reservation data to the backend
                }
            }
        });
    }

    // Method to send reservation data to the backend API
    private void sendUserToBackend(ReservationData user) {
        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);  // Create API interface
        Call<ReservationData> call = apiInterface.createReservation(user);  // Call the API to create a reservation
        call.enqueue(new Callback<ReservationData>() {
            @Override
            public void onResponse(Call<ReservationData> call, Response<ReservationData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Long id = response.body().getId();  // Get the reservation ID
                    Log.d("API_RESPONSE", "User saved with ID: " + id);
                } else {
                    Log.e("API_ERROR", "Failed to save user. HTTP Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ReservationData> call, Throwable t) {
                Log.e("API", "Error: " + t.getMessage());  // Log API failure
            }
        });
    }

    // Method to save guest details to the server
    private void saveGuestDetails(List<GuestDetails> input) {
        Log.d("GuestList", "Starting API call to save guest details");

        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);  // Create API interface
        Call<Void> call = apiInterface.saveInputs(input);  // Call the API to save guest details

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("GuestList", "API call response received");

                if (response.isSuccessful()) {
                    Log.d("GuestList", "Data saved successfully");
                    Toast.makeText(GuestList.this, "Data saved successfully", Toast.LENGTH_LONG).show();
                    // Navigate to the confirmation page with reservation number
                    Intent showGuestList = new Intent(GuestList.this, ConfirmationPage.class);
                    showGuestList.putExtra("reservationNumber", reservationNumber);  // Pass reservation number
                    startActivity(showGuestList);
                } else {
                    Log.d("GuestList", "Failed to save data, response code: " + response.code());
                    Toast.makeText(GuestList.this, "Failed to save data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("GuestList", "API call failed", t);  // Log API failure
                Toast.makeText(GuestList.this, "Network error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
