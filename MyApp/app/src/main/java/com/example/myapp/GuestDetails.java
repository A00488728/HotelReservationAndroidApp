package com.example.myapp;

public class GuestDetails {
    private String name;
    private Boolean male;
    private Boolean female;

    public GuestDetails(String name, Boolean male, Boolean female) {
        this.name = name;
        this.male = male;
        this.female = female;
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
