// src/main/java/com/example/demo/controller/UserController.java
package com.example.HotelReservationApp.Controller;

import com.example.HotelReservationApp.Entity.ReservationData;
import com.example.HotelReservationApp.Entity.AdditionalGuest;
import com.example.HotelReservationApp.Repository.ReservationRepository;
import com.example.HotelReservationApp.Service.ReservationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.time.LocalDate;



@RestController
public class HotelController {
	
	
	

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/user")
    public ReservationData getUser() {
        // For simplicity, return a hardcoded user or retrieve from DB
    	//String confirmationNumber = generateUniqueConfirmationNumber();
    	String confirmationNumber ="123456";
    	LocalDate checkinDate = LocalDate.of(2024, 10, 01), checkoutDate = LocalDate.of(2024, 11, 01);
        ReservationData user = new ReservationData(confirmationNumber, "John Doe", "john.doe@example.com", "Female", 3, checkoutDate, checkinDate);
        return user;
    }
    
    @PostMapping("/user")
    public ResponseEntity<ReservationData> createUser(@RequestBody ReservationData reservationData) {
        // Generate a unique confirmation number
        
        
        // For simplicity, let's assume we validate and save the ReservationData to a DB here
        LocalDate checkinDate = reservationData.getCheckinDate();
        LocalDate checkoutDate = reservationData.getCheckoutDate();
        
        // Create new ReservationData object with generated confirmation number
        ReservationData newUserReservation = new ReservationData(
        		"12345", 
                reservationData.getName(), 
                reservationData.getEmail(),
                reservationData.getGender(),
                reservationData.getGuestNumber(),
                checkoutDate,
                checkinDate
        );
        
        // Optionally, save this new reservation in a database or service layer
        
        return new ResponseEntity<>(newUserReservation, HttpStatus.CREATED);
    }


    
    
}

