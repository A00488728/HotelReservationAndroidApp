package com.example.myapp;

// Data class representing a hotel reservation
public class ReservationData {

    // Fields (properties) of the reservation
    private Long id;                        // Optional internal ID (may be used later)
    private String confirmationNumber;      // Confirmation number of the reservation
    private int guestNumber;                // Number of guests
    private String hotelName;               // Name of the hotel
    private String checkoutDate;            // Checkout date (could use LocalDate for type safety)
    private String checkinDate;             // Check-in date
    private String reservationNumber;       // Reservation reference number
    private int roomQuantity;               // Number of rooms reserved
    private String city;                    // City where the hotel is located

    // Constructor to initialize most of the fields (excluding id)
    public ReservationData(
            String confirmationNumber,
            int guestNumber,
            String hotelName,
            String checkoutDate,
            String checkinDate,
            String reservationNumber,
            int roomQuantity,
            String city
    ) {
        this.confirmationNumber = confirmationNumber;
        this.guestNumber = guestNumber;
        this.hotelName = hotelName;
        this.checkoutDate = checkoutDate;
        this.checkinDate = checkinDate;
        this.reservationNumber = reservationNumber;
        this.roomQuantity = roomQuantity;
        this.city = city;
    }

    // Getter and setter for roomQuantity
    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }

    // Getter and setter for city
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Getter for id (no setter - implies it's auto-generated or managed elsewhere)
    public Long getId() {
        return id;
    }

    // Getter and setter for confirmationNumber
    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    // Getter and setter for guestNumber
    public int getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(int guestNumber) {
        this.guestNumber = guestNumber;
    }

    // Getter and setter for hotelName
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    // Getter and setter for checkoutDate
    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    // Getter and setter for checkinDate
    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    // Getter and setter for reservationNumber
    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }
}
