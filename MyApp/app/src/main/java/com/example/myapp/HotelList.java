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

    ListView listView;
    ArrayList<Hotel> hotelarraylist;
    private static MyCustomAdapter adapter;
    private final Hotel[] selectedHotel = new Hotel[1]; // Use an array to allow mutation in lambdas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        Intent intentReceived = getIntent();
        String city_received = intentReceived.getStringExtra("city_name_entered");
        int rooms_received = intentReceived.getIntExtra("rooms", 0);
        int guests_received = intentReceived.getIntExtra("guests", 0);
        String checkinDate = intentReceived.getStringExtra("startDate");
        String checkoutDate = intentReceived.getStringExtra("endDate");

        listView = findViewById(R.id.HotelList);
        Button bookbtn = findViewById(R.id.BookButton);

        if (!city_received.isEmpty()) {
            HotelSearchHelper.searchHotelsInCity(
                    city_received,
                    checkinDate,
                    checkoutDate,
                    guests_received,
                    "0,17",
                    rooms_received,
                    "EUR",
                    "en-us",
                    new HotelSearchHelper.HotelSearchCallback() {
                        @Override
                        public void onHotelsFound(List<Hotel> hotelList) {
                            runOnUiThread(() -> {
                                hotelarraylist = new ArrayList<>(hotelList);
                                adapter = new MyCustomAdapter(hotelarraylist, HotelList.this);
                                listView.setAdapter(adapter);

                                // Handle list item click
                                listView.setOnItemClickListener((parent, view, position, id) -> {
                                    selectedHotel[0] = hotelarraylist.get(position);
                                    Toast.makeText(getApplicationContext(),
                                            "Selected: " + selectedHotel[0].getName(),
                                            Toast.LENGTH_LONG).show();
                                });
                            });
                        }

                        @Override
                        public void onError(String errorMessage) {
                            runOnUiThread(() ->
                                    Toast.makeText(HotelList.this, "Error: " + errorMessage, Toast.LENGTH_LONG).show());
                        }
                    });
        } else {
            Toast.makeText(this, "Please enter a city name.", Toast.LENGTH_SHORT).show();
        }

        // Book button click
        bookbtn.setOnClickListener(v -> {
            if (selectedHotel[0] == null) {
                Toast.makeText(HotelList.this, "Please select a hotel first.", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent showGuestList = new Intent(HotelList.this, GuestList.class);
            showGuestList.putExtra("selected_hotel", selectedHotel[0]);
            showGuestList.putExtra("city_received", city_received);
            showGuestList.putExtra("rooms_received", rooms_received);
            showGuestList.putExtra("guests_received", guests_received);
            showGuestList.putExtra("startDate", checkinDate);
            showGuestList.putExtra("endDate", checkoutDate);

            Log.d("IntentData", "Hotel Selected: " + selectedHotel[0].getName());
            startActivity(showGuestList);
        });
    }
}
