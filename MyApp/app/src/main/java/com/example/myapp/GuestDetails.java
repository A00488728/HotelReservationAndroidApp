package com.example.myapp;

public class GuestDetails {

    // Private member variables to hold guest details
    private String name;  // Name of the guest
    private Boolean male;  // Boolean indicating if the guest is male
    private Boolean female;  // Boolean indicating if the guest is female

    private String reservationNumber;  // Reservation number associated with the guest

    // Getter method to retrieve the reservation number
    public String getConfirmationNumber() {
        return reservationNumber;  // Return the reservation number
    }

    // Setter method to set the reservation number
    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;  // Assign the reservation number
    }

    // Constructor to initialize a GuestDetails object with name, gender, and reservation number
    public GuestDetails(String name, Boolean male, Boolean female, String reservationNumber) {
        this.name = name;  // Initialize guest's name
        this.male = male;  // Initialize guest's gender (male)
        this.female = female;  // Initialize guest's gender (female)
        this.reservationNumber = reservationNumber;  // Initialize reservation number
    }

    // Getter method to retrieve the guest's name
    public String getName() {
        return name;  // Return the guest's name
    }

    // Setter method to set the guest's name
    public void setName(String name) {
        this.name = name;  // Assign the guest's name
    }

    // Getter method to retrieve if the guest is male
    public Boolean getMale() {
        return male;  // Return true if the guest is male, else false
    }

    // Setter method to set the guest's male status
    public void setMale(Boolean male) {
        this.male = male;  // Assign the male gender status
    }

    // Getter method to retrieve if the guest is female
    public Boolean getFemale() {
        return female;  // Return true if the guest is female, else false
    }

    // Setter method to set the guest's female status
    public void setFemale(Boolean female) {
        this.female = female;  // Assign the female gender status
    }
}
