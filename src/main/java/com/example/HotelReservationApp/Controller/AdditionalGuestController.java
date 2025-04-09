package com.example.HotelReservationApp.Controller;

import com.example.HotelReservationApp.Entity.AdditionalGuest;
import com.example.HotelReservationApp.Service.AdditionalGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guests")
public class AdditionalGuestController {

    private final AdditionalGuestService additionalGuestService;

    @Autowired
    public AdditionalGuestController(AdditionalGuestService additionalGuestService) {
        this.additionalGuestService = additionalGuestService;
    }

    // 1. Create a new AdditionalGuest
    @PostMapping
    public ResponseEntity<AdditionalGuest> createGuest(@RequestBody AdditionalGuest additionalGuest) {
        AdditionalGuest createdGuest = additionalGuestService.createGuest(additionalGuest);
        return new ResponseEntity<>(createdGuest, HttpStatus.CREATED);
    }

    // 2. Get all AdditionalGuests
    @GetMapping
    public ResponseEntity<List<AdditionalGuest>> getAllGuests() {
        List<AdditionalGuest> guests = additionalGuestService.getAllGuests();
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }

    // 3. Get an AdditionalGuest by id
    @GetMapping("/{id}")
    public ResponseEntity<AdditionalGuest> getGuestById(@PathVariable int id) {
        Optional<AdditionalGuest> guest = additionalGuestService.getGuestById(id);
        if (guest.isPresent()) {
            return new ResponseEntity<>(guest.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 4. Update an existing AdditionalGuest
    @PutMapping("/{id}")
    public ResponseEntity<AdditionalGuest> updateGuest(@PathVariable int id, @RequestBody AdditionalGuest updatedGuest) {
        Optional<AdditionalGuest> existingGuest = additionalGuestService.getGuestById(id);
        if (existingGuest.isPresent()) {
            updatedGuest.setId(id); // Ensure the ID is preserved for the update
            AdditionalGuest guest = additionalGuestService.updateGuest(updatedGuest);
            return new ResponseEntity<>(guest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 5. Delete an AdditionalGuest by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable int id) {
        Optional<AdditionalGuest> guest = additionalGuestService.getGuestById(id);
        if (guest.isPresent()) {
            additionalGuestService.deleteGuest(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
