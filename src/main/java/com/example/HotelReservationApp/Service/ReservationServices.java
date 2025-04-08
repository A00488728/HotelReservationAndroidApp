package com.example.HotelReservationApp.Service;

import com.example.HotelReservationApp.Repository.ReservationRepository;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ReservationServices{
	
    @Autowired
    private ReservationRepository reservationRepository;
	
	private static Set<String> generatedNumbers = new HashSet<>();
	
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

