package com.example.HotelReservationApp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class AdditionalGuest
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int resrvationNumber;
	private String guestName;
	private String gender;
	
	//constructors
	
	public AdditionalGuest() {
		
	}
	
	public AdditionalGuest(int id, int reservationNumber, String guestName, String gender)
	{
		this.id=id;
		this.resrvationNumber=reservationNumber;
		this.guestName=guestName;
		this.gender=gender;
	}
	
	//getters and setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getResrvationNumber() {
		return resrvationNumber;
	}
	public void setResrvationNumber(int resrvationNumber) {
		this.resrvationNumber = resrvationNumber;
	}
	public String getGuestName() {
		return guestName;
	}
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	
	
}