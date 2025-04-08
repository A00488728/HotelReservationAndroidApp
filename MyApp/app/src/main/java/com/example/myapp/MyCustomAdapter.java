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

public class MyCustomAdapter extends ArrayAdapter<Hotel> {

    private ArrayList<Hotel> HotelArrayList;
    Context context;

    public MyCustomAdapter(ArrayList<Hotel> HotelArrayList, Context context){
        super(context, R.layout.hotel_list_layout, HotelArrayList);
        this.HotelArrayList = HotelArrayList;
        this.context = context;
    }

    private static class MyViewHolder{
        TextView Hotel_Name;
        TextView City_Name;
        TextView Hotel_Price;
        TextView Hotel_Availability;
        TextView Hotel_Distance;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Hotel hotel = getItem(position);

        MyViewHolder myViewHolder;
        final View result;
        if (convertView == null) {
            myViewHolder = new MyViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(
                    R.layout.hotel_list_layout,
                    parent,
                    false

            );
            myViewHolder.Hotel_Name = (TextView) convertView.findViewById(R.id.HotelName);
            myViewHolder.City_Name = (TextView) convertView.findViewById(R.id.CityName);
            myViewHolder.Hotel_Price = (TextView) convertView.findViewById(R.id.HotelPrice);
            myViewHolder.Hotel_Availability = (TextView) convertView.findViewById(R.id.HotelAvailability);
            myViewHolder.Hotel_Distance = (TextView) convertView.findViewById(R.id.HotelDistance);

            result = convertView;

            convertView.setTag(myViewHolder);
        }
        else{
            myViewHolder = (MyViewHolder) convertView.getTag();
            result = convertView;

        }

        myViewHolder.Hotel_Name.setText(hotel.getName());
        myViewHolder.City_Name.setText(hotel.getCity());
        myViewHolder.Hotel_Price.setText(hotel.getPrice());
        myViewHolder.Hotel_Availability.setText(hotel.getAvailability());
        myViewHolder.Hotel_Distance.setText(hotel.getDistance());


        return convertView;
    }
}
