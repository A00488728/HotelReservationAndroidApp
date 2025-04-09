package com.example.HotelReservationApp.Service;

import com.example.HotelReservationApp.Entity.AdditionalGuest;
import com.example.HotelReservationApp.Repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdditionalGuestService {

    private final GuestRepository additionalGuestRepository;

    @Autowired
    public AdditionalGuestService(GuestRepository additionalGuestRepository) {
        this.additionalGuestRepository = additionalGuestRepository;
    }

    // Create a new AdditionalGuest
    public AdditionalGuest createGuest(AdditionalGuest additionalGuest) {
        return additionalGuestRepository.save(additionalGuest);
    }

    // Get all AdditionalGuests
    public List<AdditionalGuest> getAllGuests() {
        return additionalGuestRepository.findAll();
    }

    // Get AdditionalGuest by ID
    public Optional<AdditionalGuest> getGuestById(int id) {
        return additionalGuestRepository.findById(id);
    }

    // Update an AdditionalGuest
    public AdditionalGuest updateGuest(AdditionalGuest updatedGuest) {
        return additionalGuestRepository.save(updatedGuest);
    }

    // Delete an AdditionalGuest
    public void deleteGuest(int id) {
        additionalGuestRepository.deleteById(id);
    }
}
