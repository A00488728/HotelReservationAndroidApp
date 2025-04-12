package com.example.myapp;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Custom RecyclerView Adapter for handling a list of guest details
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewHolder> {

    // List of GuestDetails objects (data model)
    private List<GuestDetails> itemList;

    // Exposes the list to other classes (e.g. Activity/Fragment can retrieve input data)
    public List<GuestDetails> getGuestDetails() {
        return itemList;
    }

    // Constructor to initialize adapter with data
    public MyRecyclerViewAdapter(List<GuestDetails> itemList) {
        this.itemList = itemList;
    }

    // Inflate the custom item layout and return the ViewHolder
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.guest_list_layout, parent, false);
        return new MyRecyclerViewHolder(view);
    }

    // Bind data to each item in the list and set up listeners for interaction
    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHolder holder, int position) {
        GuestDetails item = itemList.get(position); // Get the current guest data

        // Pre-fill views with existing data
        holder.guestName.setText(item.getName());
        holder.guestMale.setChecked(item.getMale());
        holder.guestFemale.setChecked(item.getFemale());

        // Update model when male radio button is checked/unchecked
        holder.guestMale.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setMale(isChecked);
        });

        // Update model when female radio button is checked/unchecked
        holder.guestFemale.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setFemale(isChecked);
        });

        // Update model when guest name is changed
        holder.guestName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Update guest name in the data model
                item.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not used
            }
        });
    }

    // Return the total number of items in the list
    @Override
    public int getItemCount(){
        return itemList.size();
    }

    // ViewHolder class that holds references to UI elements in each row
    public static class MyRecyclerViewHolder extends RecyclerView.ViewHolder {

        EditText guestName;
        RadioButton guestMale;
        RadioButton guestFemale;

        public MyRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            // Connect ViewHolder properties with views from guest_list_layout.xml
            guestName = itemView.findViewById(R.id.enterName);
            guestMale = itemView.findViewById(R.id.radioButton1);
            guestFemale = itemView.findViewById(R.id.radioButton2);
        }
    }
}
