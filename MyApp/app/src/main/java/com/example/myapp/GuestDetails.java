package com.example.myapp;

public class GuestDetails {
    private String name;
    private Boolean male;
    private Boolean female;

    private String confirmationNumber;

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public GuestDetails(String name, Boolean male, Boolean female, String confirmationNumber) {
        this.name = name;
        this.male = male;
        this.female = female;
        this.confirmationNumber = confirmationNumber;
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
