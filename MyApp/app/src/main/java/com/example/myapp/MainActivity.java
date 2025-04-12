package com.example.myapp;

// Importing necessary Android components
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// Material Design date picker component
import com.google.android.material.datepicker.MaterialDatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // UI references
    Button showRangePicker;       // Button to trigger the date range picker
    TextView selectedRangeText;   // TextView to display selected date range

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Load the layout

        // Initialize a Material Date Range Picker with a custom theme and title
        MaterialDatePicker<Pair<Long, Long>> dateRangePicker =
                MaterialDatePicker.Builder.dateRangePicker()
                        .setTheme(R.style.CustomMaterialDatePickerTheme2) // Custom style defined in styles.xml
                        .setTitleText("Select Dates")                     // Title shown in picker dialog
                        .build();

        // Link UI elements to variables
        showRangePicker = findViewById(R.id.showRangePicker);
        selectedRangeText = findViewById(R.id.selectedRangeText);

        // When the button is clicked, show the date range picker dialog
        showRangePicker.setOnClickListener(v -> {
            dateRangePicker.show(getSupportFragmentManager(), "DATE_RANGE_PICKER");
        });

        // This array holds the final formatted start and end dates
        final String[] dates ={"", ""};

        // Triggered when user selects a date range and clicks "OK"
        dateRangePicker.addOnPositiveButtonClickListener(selection -> {
            Long startDate = selection.first; // Start timestamp in milliseconds
            Long endDate = selection.second;  // End timestamp in milliseconds

            // Create Calendar instance to manipulate the start date
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTimeInMillis(startDate);
            startCalendar.set(Calendar.HOUR_OF_DAY, 0);   // Normalize time to midnight
            startCalendar.set(Calendar.MINUTE, 0);
            startCalendar.set(Calendar.SECOND, 0);
            startCalendar.set(Calendar.MILLISECOND, 0);
            startCalendar.add(Calendar.DAY_OF_YEAR, 1);   // Add 1 day for display accuracy

            // Similarly, normalize and adjust the end date
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTimeInMillis(endDate);
            endCalendar.set(Calendar.HOUR_OF_DAY, 0);
            endCalendar.set(Calendar.MINUTE, 0);
            endCalendar.set(Calendar.SECOND, 0);
            endCalendar.set(Calendar.MILLISECOND, 0);
            endCalendar.add(Calendar.DAY_OF_YEAR, 1);

            // Convert Calendar dates to formatted strings
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String startDateString = sdf.format(startCalendar.getTime());
            String endDateString = sdf.format(endCalendar.getTime());

            // Debug logs for developer
            Log.d("DateRangePicker", "Formatted Start Date: " + startDateString);
            Log.d("DateRangePicker", "Formatted End Date: " + endDateString);

            // Store the formatted dates in the array
            dates[0] = startDateString;
            dates[1] = endDateString;

            // Display selected date range to the user
            selectedRangeText.setText("Selected Range: " + dateRangePicker.getHeaderText());
        });

        // Button that triggers the search and sends data to next activity
        Button btn = findViewById(R.id.HotelSearch);

        // Handle click on "Search" button
        btn.setOnClickListener(v -> {

            // Retrieve user input from EditText fields
            EditText city_name = findViewById(R.id.enterCity);
            String city_name_entered = city_name.getText().toString();

            EditText number_of_guest = findViewById(R.id.enterNumberOfGuest);
            String number_of_guest_entered = number_of_guest.getText().toString();

            EditText number_of_rooms = findViewById(R.id.enterNumberOfRooms);
            String number_of_rooms_entered = number_of_rooms.getText().toString();

            Integer guests = 0, rooms = 0;

            // Validate city input - should not be empty and only letters/spaces
            if (city_name_entered.trim().length() == 0 || !city_name_entered.matches("[a-zA-Z ]+")) {
                Toast.makeText(this, "Enter a valid Destination", Toast.LENGTH_LONG).show();
            } else {
                // Try to parse number of guests
                try {
                    guests = Integer.parseInt(number_of_guest_entered);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid Number of Guests", Toast.LENGTH_LONG).show();
                }

                // Try to parse number of rooms
                try {
                    rooms = Integer.parseInt(number_of_rooms_entered);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid Number of Rooms", Toast.LENGTH_LONG).show();
                }

                // If inputs are valid, prepare to send them to HotelList activity
                Intent i = new Intent(MainActivity.this, HotelList.class);
                i.putExtra("city_name_entered", city_name_entered); // Destination city
                i.putExtra("rooms", rooms);                         // Number of rooms
                i.putExtra("guests", guests);                       // Number of guests
                i.putExtra("startDate", dates[0]);                  // Start date of stay
                i.putExtra("endDate", dates[1]);                    // End date of stay

                // Debug logs
                Log.d("IntentData", "City Name Entered: " + city_name_entered);
                Log.d("IntentData", "Rooms: " + rooms);
                Log.d("IntentData", "Guests: " + guests);

                // Navigate to the HotelList activity
                startActivity(i);
            }
        });
    }
}
