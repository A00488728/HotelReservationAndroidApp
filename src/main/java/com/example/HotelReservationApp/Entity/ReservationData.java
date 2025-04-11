// src/main/java/com/example/demo/entity/User.java
package com.example.HotelReservationApp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ReservationData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String confirmationNumber;
    private int guestNumber;
    private String hotelName;
    private String checkoutDate;
    private String checkinDate;
    private String reservationNumber;
    private int roomQuantity;
    private String city;
    

	// Constructors
    public ReservationData() {
    }



    public ReservationData(Long id, String confirmationNumber, int guestNumber, String hotelName, String checkoutDate,
			String checkinDate, String reservationNumber, int roomQuantity, String city) {
		super();
		this.id = id;
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



	public String getCheckoutDate() {
		return checkoutDate;
	}



	public String getCheckinDate() {
		return checkinDate;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
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

	// Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
