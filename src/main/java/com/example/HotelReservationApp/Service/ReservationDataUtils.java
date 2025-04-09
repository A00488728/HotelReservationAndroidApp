// src/main/java/com/example/demo/utils/ReservationDataUtils.java
package com.example.HotelReservationApp.Service;

import java.util.Random;

public class ReservationDataUtils {

    // Method to generate a random confirmation number
    public static String generateConfirmationNumber() {
        // Define characters (digits + upper case letters)
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();

        // Length of the confirmation number (adjust as needed)
        int length = 10;

        StringBuilder confirmationNumber = new StringBuilder(length);

        // Generate a random confirmation number
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            confirmationNumber.append(characters.charAt(index));
        }

        return confirmationNumber.toString();
    }
}
