package com.example.HotelReservationApp.Model;

public class GuestDetails {
    private String name;
	private boolean male;
    private boolean female;
    
    public GuestDetails() {
    	
    }
    
	public GuestDetails(String name, boolean male, boolean female) {
		super();
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
	
	
    
    


}
