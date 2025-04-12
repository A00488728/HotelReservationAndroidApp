package com.example.myapp;

import java.io.Serializable;  // Importing Serializable interface to allow object serialization
import java.time.LocalDate;  // Importing LocalDate for handling dates (though it's not used in this class)

// Hotel class represents a hotel object with attributes such as name, city, price, availability, and distance
public class Hotel implements Serializable {

    private String Name;  // Name of the hotel
    private String City;  // City where the hotel is located
    private String Price;  // Price of the hotel (as a string, could represent currency and amount)
    private String Availability;  // Availability status of the hotel (e.g., "Available" or "Sold Out")
    private String Distance;  // Distance of the hotel from a certain location (e.g., the center of the city)

    // Constructor to initialize the hotel object with the provided parameters
    public Hotel(String Name, String City, String Price, String Availability, String Distance) {
        this.Name = Name;  // Assigning the Name field
        this.City = City;  // Assigning the City field
        this.Price = Price;  // Assigning the Price field
        this.Availability = Availability;  // Assigning the Availability field
        this.Distance = Distance;  // Assigning the Distance field
    }

    // Getter method for the Name field
    public String getName() {
        return Name;  // Returns the name of the hotel
    }

    // Setter method for the Name field
    public void setName(String name) {
        Name = name;  // Sets the name of the hotel
    }

    // Getter method for the Price field
    public String getPrice() {
        return Price;  // Returns the price of the hotel
    }

    // Setter method for the Price field
    public void setPrice(String price) {
        Price = price;  // Sets the price of the hotel
    }

    // Getter method for the Availability field
    public String getAvailability() {
        return Availability;  // Returns the availability status of the hotel
    }

    // Setter method for the Availability field
    public void setAvailability(String availability) {
        Availability = availability;  // Sets the availability status of the hotel
    }

    // Getter method for the Distance field
    public String getDistance() {
        return Distance;  // Returns the distance of the hotel from a reference point
    }

    // Setter method for the Distance field
    public void setDistance(String distance) {
        Distance = distance;  // Sets the distance of the hotel
    }

    // Getter method for the City field
    public String getCity() {
        return City;  // Returns the city where the hotel is located
    }

    // Setter method for the City field
    public void setCity(String city) {
        City = city;  // Sets the city where the hotel is located
    }
}
