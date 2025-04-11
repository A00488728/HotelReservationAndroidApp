package com.example.HotelReservationApp.Model;

public class GuestDetails {
    private String name;
	private boolean male;
    private boolean female;
    private String reservationNumber;
    
    public GuestDetails() {
    	
    }
    
	public GuestDetails(String name, boolean male, boolean female, String reservationNumber) {
		super();
		this.name = name;
		this.male = male;
		this.female = female;
		this.reservationNumber = reservationNumber;
		
		
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public boolean isFemale() {
		return female;
	}

	public void setFemale(boolean female) {
		this.female = female;
	}
	
	public String getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(String reservationNumber) {
		this.reservationNumber = reservationNumber;
	}
	
	
    
    


}
