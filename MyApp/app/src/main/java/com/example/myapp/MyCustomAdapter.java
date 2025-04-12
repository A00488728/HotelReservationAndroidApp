package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

// Custom ArrayAdapter to display a list of Hotel objects
public class MyCustomAdapter extends ArrayAdapter<Hotel> {

    private ArrayList<Hotel> HotelArrayList; // List of Hotel objects to display
    Context context;

    // Constructor to initialize the adapter with data and context
    public MyCustomAdapter(ArrayList<Hotel> HotelArrayList, Context context){
        // R.layout.hotel_list_layout is the layout for each item in the list
        super(context, R.layout.hotel_list_layout, HotelArrayList);
        this.HotelArrayList = HotelArrayList;
        this.context = context;
    }

    // ViewHolder pattern for performance optimization
    // Holds references to the views inside each list item layout
    private static class MyViewHolder {
        TextView Hotel_Name;
        TextView City_Name;
        TextView Hotel_Price;
        TextView Hotel_Availability;
        TextView Hotel_Distance;
    }

    // Called by the system to render each item in the ListView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Get the current Hotel item
        Hotel hotel = getItem(position);
        MyViewHolder myViewHolder;
        final View result;

        // If this is the first time this view is being created
        if (convertView == null) {
            myViewHolder = new MyViewHolder();

            // Inflate the custom layout for the list item
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.hotel_list_layout, parent, false);

            // Bind the view elements to the ViewHolder
            myViewHolder.Hotel_Name = convertView.findViewById(R.id.HotelName);
            myViewHolder.City_Name = convertView.findViewById(R.id.CityName);
            myViewHolder.Hotel_Price = convertView.findViewById(R.id.HotelPrice);
            myViewHolder.Hotel_Availability = convertView.findViewById(R.id.HotelAvailability);
            myViewHolder.Hotel_Distance = convertView.findViewById(R.id.HotelDistance);

            result = convertView;

            // Store the ViewHolder with the view to reuse it later
            convertView.setTag(myViewHolder);
        } else {
            // Reuse the existing view and its ViewHolder to save memory
            myViewHolder = (MyViewHolder) convertView.getTag();
            result = convertView;
        }

        // Bind data from the Hotel object to the UI components
        myViewHolder.Hotel_Name.setText(hotel.getName());
        myViewHolder.City_Name.setText(hotel.getCity());
        myViewHolder.Hotel_Price.setText(hotel.getPrice());
        myViewHolder.Hotel_Availability.setText(hotel.getAvailability());
        myViewHolder.Hotel_Distance.setText(hotel.getDistance());

        return convertView;
    }
}
