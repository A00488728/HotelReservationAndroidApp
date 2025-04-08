package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GuestList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    private List<GuestDetails> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guest_list);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // You can set whether each item has its RadioButton selected or not
            itemList.add(new GuestDetails("Item " + (i + 1), false, false)); // All RadioButtons initially unselected
        }

        adapter = new MyRecyclerViewAdapter(itemList);
        recyclerView.setAdapter(adapter);

        Button submitbtn = findViewById(R.id.submit);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showGuestList = new Intent(GuestList.this, ConfirmationPage.class);
                startActivity(showGuestList);
            }
        });


    }
}