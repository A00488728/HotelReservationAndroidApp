// src/main/java/com/example/demo/entity/User.java
package com.example.HotelReservationApp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class ReservationData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String confirmationNumber;
    private String name;
    private String email;
    private String gender;
    private int guestNumber;
    private LocalDate checkoutDate;
    private LocalDate checkinDate;
    

	// Constructors
    public ReservationData() {
    }

    public ReservationData(String confirmationNumber, String name, String email, String gender, int guestNumber, LocalDate checkoutDate, LocalDate checkinDate) {
        this.confirmationNumber = confirmationNumber;
    	this.name = name;
        this.email = email;
        this.gender = gender;
        this.guestNumber = guestNumber;
        this.checkoutDate = checkoutDate;
        this.checkinDate = checkinDate;
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getGuestNumber() {
		return guestNumber;
	}

	public void setGuestNumber(int guestNumber) {
		this.guestNumber = guestNumber;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public LocalDate getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(LocalDate checkinDate) {
		this.checkinDate = checkinDate;
	}
}
