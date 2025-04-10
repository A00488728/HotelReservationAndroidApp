package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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

        Button bookbtn = findViewById(R.id.BookButton);

        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showGuestList = new Intent(HotelList.this, GuestList.class);
                startActivity(showGuestList);
                finish();
            }
        });


    }
}