package com.example.myapp;

public class GuestDetails {
    private String name;
    private Boolean male;
    private Boolean female;

    private String reservationNumber;

    public String getConfirmationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public GuestDetails(String name, Boolean male, Boolean female, String reservationNumber) {
        this.name = name;
        this.male = male;
        this.female = female;
        this.reservationNumber = reservationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public Boolean getFemale() {
        return female;
    }

    public void setFemale(Boolean female) {
        this.female = female;
    }
}
