package com.example.HotelReservationApp.Model;

public class GuestDetails {
    private String name;
	private boolean male;
    private boolean female;
    private String confirmationNumber;
    
    public GuestDetails() {
    	
    }
    
	public GuestDetails(String name, boolean male, boolean female, String confirmationNumber) {
		super();
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
	
	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}
	
	
    
    


}
