package com.example.HotelReservationApp.Service;

import com.example.HotelReservationApp.Model.GuestDetails;
import com.example.HotelReservationApp.Entity.AdditionalGuest;
import com.example.HotelReservationApp.Repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestDetailsService {

    private final GuestRepository inputRepository;
    
    @Autowired
    public GuestDetailsService(GuestRepository inputRepository) {
        this.inputRepository = inputRepository;
    }
    
    public void saveInputs(List<GuestDetails> inputs) {
    	
        for (GuestDetails userInput : inputs) {
            AdditionalGuest inputEntity = new AdditionalGuest();
            inputEntity.setGuestName(userInput.getName());
            String find_gender;
            if (userInput.isMale())
            {
            	find_gender="Male";
            }
            else
            	find_gender="Female";
            inputEntity.setGender(find_gender);
            inputEntity.setReservationNumber(userInput.getReservationNumber());
            inputRepository.save(inputEntity);
        }
    }
}
