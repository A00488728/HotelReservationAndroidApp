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

public class HotelList extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        // Find the ListView by ID
        listView = findViewById(R.id.HotelList);

        // Sample data for the ListView
        String[] hotels = {"Hotel 1", "Hotel 2", "Hotel 3", "Hotel 4", "Hotel 5"};

        // Create an ArrayAdapter to bind the data to the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hotels);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);

        Button bookbtn = findViewById(R.id.BookButton);

        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent confirmationPage = new Intent(HotelList.this, ConfirmationPage.class);
                startActivity(confirmationPage);
            }
        });


    }
}