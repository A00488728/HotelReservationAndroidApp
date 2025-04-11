package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button showRangePicker;
    TextView selectedRangeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create Material Date Range Picker with custom theme
        MaterialDatePicker<Pair<Long, Long>> dateRangePicker =
                MaterialDatePicker.Builder.dateRangePicker()
                        .setTheme(R.style.CustomMaterialDatePickerTheme2)
                        .setTitleText("Select Dates")
                        .build();

        showRangePicker = findViewById(R.id.showRangePicker);
        selectedRangeText = findViewById(R.id.selectedRangeText);

        // Show date range picker when button is clicked
        showRangePicker.setOnClickListener(v -> {
            dateRangePicker.show(getSupportFragmentManager(), "DATE_RANGE_PICKER");
        });

        final String[] dates ={"", ""};

        // Handle the selected range
        dateRangePicker.addOnPositiveButtonClickListener(selection -> {
            // Format: Apr 9 – Apr 15
            Long startDate = selection.first;
            Long endDate = selection.second;

            // Normalize the start date to midnight of the selected date (local timezone)
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTimeInMillis(startDate);
            startCalendar.set(Calendar.HOUR_OF_DAY, 0);
            startCalendar.set(Calendar.MINUTE, 0);
            startCalendar.set(Calendar.SECOND, 0);
            startCalendar.set(Calendar.MILLISECOND, 0);

            // Add 1 day to the start date
            startCalendar.add(Calendar.DAY_OF_YEAR, 1);

            // Adjust the end date to midnight of the next day (local timezone)
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTimeInMillis(endDate);
            endCalendar.set(Calendar.HOUR_OF_DAY, 0);
            endCalendar.set(Calendar.MINUTE, 0);
            endCalendar.set(Calendar.SECOND, 0);
            endCalendar.set(Calendar.MILLISECOND, 0);

            // Add 1 day to the end date
            endCalendar.add(Calendar.DAY_OF_YEAR, 1);

            // Format the start and end dates to a string (you can customize the format)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String startDateString = sdf.format(startCalendar.getTime());
            String endDateString = sdf.format(endCalendar.getTime());

            // Log the formatted date range
            Log.d("DateRangePicker", "Formatted Start Date: " + startDateString);
            Log.d("DateRangePicker", "Formatted End Date: " + endDateString);

            dates[0] = startDateString;
            dates[1] = endDateString;
            selectedRangeText.setText("Selected Range: " + dateRangePicker.getHeaderText());
        });
        Button btn = findViewById(R.id.HotelSearch);

        btn.setOnClickListener(v -> {

            EditText city_name = findViewById(R.id.enterCity);
            String city_name_entered = city_name.getText().toString();
            EditText number_of_guest = findViewById(R.id.enterNumberOfGuest);
            String number_of_guest_entered = number_of_guest.getText().toString();
            EditText number_of_rooms = findViewById(R.id.enterNumberOfRooms);
            String number_of_rooms_entered = number_of_rooms.getText().toString();
            Integer guests=0, rooms=0;
            if (city_name_entered.trim().length()==0 || !city_name_entered.matches("[a-zA-Z ]+"))
            {
                Toast.makeText(this, "Enter a valid Destination", Toast.LENGTH_LONG).show();
            }
            else {

                try{
                    guests = Integer.parseInt(number_of_guest_entered);
                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(this, "Invalid Number of Guests", Toast.LENGTH_LONG).show();
                }
                try{
                    rooms = Integer.parseInt(number_of_rooms_entered);
                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(this, "Invalid Number of Rooms", Toast.LENGTH_LONG).show();
                }
                Intent i = new Intent(MainActivity.this, HotelList.class);
                i.putExtra("city_name_entered", city_name_entered);
                i.putExtra("rooms", rooms);
                i.putExtra("guests", guests);
                i.putExtra("startDate", dates[0]);
                i.putExtra("endDate", dates[1]);
                Log.d("IntentData", "City Name Entered: " + city_name_entered);
                Log.d("IntentData", "Rooms: " + rooms);
                Log.d("IntentData", "Guests: " + guests);
                startActivity(i);
            }

        });
    }
}
