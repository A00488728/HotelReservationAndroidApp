package com.example.HotelReservationApp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class AdditionalGuest
{
	@Id
	private int resrvationNumber;
	private String guestName;
	
	//constructors
	
	public AdditionalGuest() {
		
	}
	
	public AdditionalGuest(int reservationNumber, String guestName)
	{
		this.resrvationNumber=reservationNumber;
		this.guestName=guestName;
	}
	
	//getters and setters
	
	public int getResrvationNumber() {
		return resrvationNumber;
	}
	public void setResrvationNumber(int resrvationNumber) {
		this.resrvationNumber = resrvationNumber;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	
}