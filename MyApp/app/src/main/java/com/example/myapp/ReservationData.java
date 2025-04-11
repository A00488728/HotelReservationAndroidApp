package com.example.myapp;

public class ReservationData {
    private Long id;
    private String confirmationNumber;
    private int guestNumber;
    private String hotelName;
    private String checkoutDate;
    private String checkinDate;
    private String reservationNumber;
    private int roomQuantity;
    private String city;

    public ReservationData(String confirmationNumber, int guestNumber, String hotelName, String checkoutDate, String checkinDate, String reservationNumber, int roomQuantity, String city) {
        this.confirmationNumber = confirmationNumber;
        this.guestNumber = guestNumber;
        this.hotelName = hotelName;
        this.checkoutDate = checkoutDate;
        this.checkinDate = checkinDate;
        this.reservationNumber = reservationNumber;
        this.roomQuantity = roomQuantity;
        this.city = city;
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(int guestNumber) {
        this.guestNumber = guestNumber;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }
}


