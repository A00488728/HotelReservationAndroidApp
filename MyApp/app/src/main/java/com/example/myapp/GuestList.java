package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    private List<GuestDetails> itemList;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 10;
    private static final SecureRandom random = new SecureRandom();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        String reservationNumber = sb.toString();

        itemList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            // You can set whether each item has its RadioButton selected or not
            itemList.add(new GuestDetails("", false, false, reservationNumber)); // All RadioButtons initially unselected
        }

        adapter = new MyRecyclerViewAdapter(itemList);
        recyclerView.setAdapter(adapter);
        recyclerView.post(() -> {
            Log.d("RecyclerView", "Total height: " + recyclerView.getHeight());
            Log.d("RecyclerView", "Scroll range: " + recyclerView.computeVerticalScrollRange());
        });

        Button submitbtn = findViewById(R.id.submit);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("GuestList", "Submit button clicked");
                int validate_done=0;
                // Get guest details from the adapter
                List<GuestDetails> inputs = adapter.getGuestDetails();

                Intent intent = getIntent();
                String cityReceived = intent.getStringExtra("city_name_entered");
                int roomsReceived = intent.getIntExtra("rooms", 0);
                int guestsReceived = intent.getIntExtra("guests", 0);
                Hotel selectedHotel = (Hotel) intent.getSerializableExtra("selected_hotel");

                ReservationData reservation_data = new ReservationData("", guestsReceived, selectedHotel.getName(), "", "", reservationNumber, roomsReceived, cityReceived);

                Log.d("GuestList", "Guest details retrieved: " + inputs.size() + " items");

                // For debugging: Show the first and last names (if needed)
                if (!inputs.isEmpty()) {
                    Log.d("GuestList", "First guest name: " + inputs.get(0).getName());
                    Log.d("GuestList", "Last guest name: " + inputs.get(inputs.size() - 1).getName());
                }

                for (GuestDetails guest : inputs)
                {
                    if (!guest.getName().matches(("[a-zA-Z]+"))){
                        Toast.makeText(GuestList.this, "Invalid guest name", Toast.LENGTH_LONG).show();
                        validate_done=0;
                        break;
                    } else if (!guest.getFemale() && !guest.getMale()) {
                        Toast.makeText(GuestList.this, "Guest gender not entered", Toast.LENGTH_LONG).show();
                        validate_done=0;
                        break;
                    }
                    else
                        validate_done=1;
                }
                // Call the method to save data
                if (validate_done==1)
                {
                    saveGuestDetails(inputs);
                    sendUserToBackend(reservation_data);
                }
            }
        });
    }
    private void sendUserToBackend(ReservationData user) {
        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<ReservationData> call = apiInterface.createReservation(user);
        call.enqueue(new Callback<ReservationData>() {
            @Override
            public void onResponse(Call<ReservationData> call, Response<ReservationData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Long id = response.body().getId();
                    Log.d("API_RESPONSE", "User saved with ID: " + id);
                } else {
                    Log.e("API_ERROR", "Failed to save user. HTTP Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ReservationData> call, Throwable t) {
                Log.e("API", "Error: " + t.getMessage());
            }
        });

    }

    // Method to save guest details to the server
    private void saveGuestDetails(List<GuestDetails> input) {
        Log.d("GuestList", "Starting API call to save guest details");

        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Void> call = apiInterface.saveInputs(input);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("GuestList", "API call response received");

                if (response.isSuccessful()) {
                    Log.d("GuestList", "Data saved successfully");
                    Toast.makeText(GuestList.this, "Data saved successfully", Toast.LENGTH_LONG).show();
                    // Navigate to the confirmation page
                    Intent showGuestList = new Intent(GuestList.this, ConfirmationPage.class);
                    startActivity(showGuestList);
                } else {
                    Log.d("GuestList", "Failed to save data, response code: " + response.code());
                    Toast.makeText(GuestList.this, "Failed to save data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("GuestList", "API call failed", t);
                Toast.makeText(GuestList.this, "Network error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
