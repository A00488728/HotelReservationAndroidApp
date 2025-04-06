// src/main/java/com/example/demo/controller/UserController.java
package com.example.HotelReservationApp.Controller;

import com.example.HotelReservationApp.Entity.ReservationData;
import com.example.HotelReservationApp.Entity.AdditionalGuest;
import com.example.HotelReservationApp.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.time.LocalDate;

@RestController
public class HotelController {
	
	private static Set<String> generatedNumbers = new HashSet<>();
	

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/user")
    public ReservationData getUser() {
        // For simplicity, return a hardcoded user or retrieve from DB
    	String confirmationNumber = generateUniqueConfirmationNumber();
    	LocalDate checkinDate = LocalDate.of(2024, 10, 01), checkoutDate = LocalDate.of(2024, 11, 01);
        ReservationData user = new ReservationData(confirmationNumber, "John Doe", "john.doe@example.com", "Female", 3, checkoutDate, checkinDate);
        return user;
    }
    

    
    public static String generateUniqueConfirmationNumber() {
        String confirmationNumber;
        
        // Keep generating a new confirmation number until it's unique
        do {
            confirmationNumber = generateConfirmationNumber();
        } while (generatedNumbers.contains(confirmationNumber));
        
        // Add the new unique confirmation number to the set
        generatedNumbers.add(confirmationNumber);
        
        return confirmationNumber;
    }
    
    private static String generateConfirmationNumber() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder confirmationNumber = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(characters.length());
            confirmationNumber.append(characters.charAt(index));
        }

        return confirmationNumber.toString();
    }
}

