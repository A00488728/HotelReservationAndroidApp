package com.example.myapp;

import java.io.Serializable;
import java.time.LocalDate;

public class Hotel implements Serializable {
    private String Name;
    private String City;
    private String Price;
    private String Availability;
    private String Distance;

    public Hotel (String Name, String City, String Price, String Availability, String Distance){
        this.Name=Name;
        this.City=City;
        this.Price=Price;
        this.Availability=Availability;
        this.Distance=Distance;


    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getAvailability() {
        return Availability;
    }

    public void setAvailability(String availability) {
        Availability = availability;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
}
