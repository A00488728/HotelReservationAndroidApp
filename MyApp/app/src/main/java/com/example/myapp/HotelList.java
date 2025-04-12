package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HotelList extends AppCompatActivity {

    ListView listView;  // ListView for displaying the hotel list
    ArrayList<Hotel> hotelarraylist;  // ArrayList to hold hotel objects
    private static MyCustomAdapter adapter;  // Custom adapter for the ListView
    private final Hotel[] selectedHotel = new Hotel[1];  // Array to store the selected hotel, used to mutate the value in lambda expressions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        // Receive data passed from the previous activity
        Intent intentReceived = getIntent();
        String city_received = intentReceived.getStringExtra("city_name_entered");  // City name entered by the user
        int rooms_received = intentReceived.getIntExtra("rooms", 0);  // Number of rooms selected
        int guests_received = intentReceived.getIntExtra("guests", 0);  // Number of guests
        String checkinDate = intentReceived.getStringExtra("startDate");  // Check-in date
        String checkoutDate = intentReceived.getStringExtra("endDate");  // Check-out date

        // Initialize the ListView and Book button
        listView = findViewById(R.id.HotelList);
        Button bookbtn = findViewById(R.id.BookButton);

        // Check if a city name is provided
        if (!city_received.isEmpty()) {
            // Call the HotelSearchHelper to search for hotels in the specified city
            HotelSearchHelper.searchHotelsInCity(
                    city_received,  // City to search hotels in
                    checkinDate,    // Check-in date
                    checkoutDate,   // Check-out date
                    guests_received, // Number of guests
                    "0,17",          // Age group for children (currently just a placeholder)
                    rooms_received,  // Number of rooms
                    "EUR",           // Currency code
                    "en-us",         // Language code
                    new HotelSearchHelper.HotelSearchCallback() {
                        @Override
                        public void onHotelsFound(List<Hotel> hotelList) {
                            // This method is called when hotels are successfully retrieved
                            runOnUiThread(() -> {
                                // Populate the hotel list
                                hotelarraylist = new ArrayList<>(hotelList);
                                // Set the custom adapter to the ListView
                                adapter = new MyCustomAdapter(hotelarraylist, HotelList.this);
                                listView.setAdapter(adapter);

                                // Set up click listener for hotel list items
                                listView.setOnItemClickListener((parent, view, position, id) -> {
                                    // Store the selected hotel in the selectedHotel array
                                    selectedHotel[0] = hotelarraylist.get(position);
                                    // Show a toast with the selected hotel name
                                    Toast.makeText(getApplicationContext(),
                                            "Selected: " + selectedHotel[0].getName(),
                                            Toast.LENGTH_LONG).show();
                                });
                            });
                        }

                        @Override
                        public void onError(String errorMessage) {
                            // This method is called if there is an error during hotel search
                            runOnUiThread(() ->
                                    Toast.makeText(HotelList.this, "Error: " + errorMessage, Toast.LENGTH_LONG).show());
                        }
                    });
        } else {
            // Show a message if the city name is empty
            Toast.makeText(this, "Please enter a city name.", Toast.LENGTH_SHORT).show();
        }

        // Set up click listener for the Book button
        bookbtn.setOnClickListener(v -> {
            // Check if a hotel is selected
            if (selectedHotel[0] == null) {
                // Show a message if no hotel is selected
                Toast.makeText(HotelList.this, "Please select a hotel first.", Toast.LENGTH_SHORT).show();
                return;
            }

            // If a hotel is selected, proceed to the next activity (GuestList)
            Intent showGuestList = new Intent(HotelList.this, GuestList.class);
            // Pass selected hotel and other data to the GuestList activity
            showGuestList.putExtra("selected_hotel", selectedHotel[0]);
            showGuestList.putExtra("city_received", city_received);
            showGuestList.putExtra("rooms_received", rooms_received);
            showGuestList.putExtra("guests_received", guests_received);
            showGuestList.putExtra("startDate", checkinDate);
            showGuestList.putExtra("endDate", checkoutDate);

            // Log the selected hotel for debugging purposes
            Log.d("IntentData", "Hotel Selected: " + selectedHotel[0].getName());
            // Start the GuestList activity
            startActivity(showGuestList);
        });
    }
}
