package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class HotelList extends AppCompatActivity {

    ListView listView;
    ArrayList<Hotel> hotelarraylist;
    private static MyCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        // Find the ListView by ID
        listView = findViewById(R.id.HotelList);

        hotelarraylist = new ArrayList<>();

        // Sample data for the ListView
        Hotel hotel1 = new Hotel("Halifax Hotel", "Halifax", "$123", "Available", "123km");
        Hotel hotel2 = new Hotel("Halifax Hotel", "Halifax", "$123", "Available", "123km");
        Hotel hotel3 = new Hotel("Halifax Hotel", "Halifax", "$123", "Available", "123km");

        hotelarraylist.add(hotel1);
        hotelarraylist.add(hotel2);
        hotelarraylist.add(hotel3);

        // Create an ArrayAdapter to bind the data to the ListView

        adapter = new MyCustomAdapter(hotelarraylist, getApplicationContext());
        // Set the adapter to the ListView
        listView.setAdapter(adapter);

        final Hotel[] hotel_selected = new Hotel[1];

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Grab the selected item
                hotel_selected[0] = hotelarraylist.get(position);
                // Do something with it (e.g., Toast or send to another activity)
                Toast.makeText(getApplicationContext(), "Selected: " + hotel_selected[0], Toast.LENGTH_LONG).show();

            }
        });

        Button bookbtn = findViewById(R.id.BookButton);
        Intent intentReceived = getIntent();
        String city_received = intentReceived.getStringExtra("city_name_entered");
        int rooms_received = intentReceived.getIntExtra("rooms", 0);
        int guests_received = intentReceived.getIntExtra("guests", 0);
        Log.d("IntentReceived", "City Received: " + city_received);
        Log.d("IntentReceived", "Rooms Received: " + rooms_received);
        Log.d("IntentReceived", "Guests Received: " + guests_received);

        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showGuestList = new Intent(HotelList.this, GuestList.class);
                showGuestList.putExtra("selected_hotel", hotel_selected[0]);
                showGuestList.putExtra("city_received", city_received);
                showGuestList.putExtra("rooms_received", rooms_received);
                showGuestList.putExtra("guests_received", guests_received);
                Log.d("IntentData", "Hotel Selected: " + hotel_selected[0]);
                Log.d("IntentData", "City Received: " + city_received);
                Log.d("IntentData", "Rooms Received: " + rooms_received);
                Log.d("IntentData", "Guests Received: " + guests_received);
                startActivity(showGuestList);
            }
        });


    }
}