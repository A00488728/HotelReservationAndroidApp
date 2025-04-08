package com.example.myapp;

public class GuestDetails {
    private String Guest_Name;
    private Boolean Guest_Male;
    private Boolean Guest_Female;

    public GuestDetails(String guest_Name, Boolean guest_Male, Boolean guest_Female) {
        Guest_Name = guest_Name;
        Guest_Male = guest_Male;
        Guest_Female = guest_Female;
    }

    public String getGuest_Name() {
        return Guest_Name;
    }

    public void setGuest_Name(String guest_Name) {
        Guest_Name = guest_Name;
    }

    public Boolean getGuest_Male() {
        return Guest_Male;
    }

    public void setGuest_Male(Boolean guest_Male) {
        Guest_Male = guest_Male;
    }

    public Boolean getGuest_Female() {
        return Guest_Female;
    }

    public void setGuest_Female(Boolean guest_Female) {
        Guest_Female = guest_Female;
    }
}
