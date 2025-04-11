// src/main/java/com/example/demo/controller/ReservationDataController.java
package com.example.HotelReservationApp.Controller;

import com.example.HotelReservationApp.Entity.ReservationData;
import com.example.HotelReservationApp.Service.ReservationDataService;
import com.example.HotelReservationApp.Service.ReservationDataUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationDataController {

    private final ReservationDataService reservationDataService;

    @Autowired
    public ReservationDataController(ReservationDataService reservationDataService) {
        this.reservationDataService = reservationDataService;
    }

    @PostMapping
    public ResponseEntity<ReservationData> createReservation(@RequestBody ReservationData reservationData) {
        // Generate a random confirmation number
        String confirmationNumber = ReservationDataUtils.generateConfirmationNumber();
        
        // Set the confirmation number in the reservation object
        reservationData.setConfirmationNumber(confirmationNumber);
        
        // Save the reservation using the service
        ReservationData createdReservation = reservationDataService.createReservation(reservationData);

        // Return the created reservation with HTTP 201 status
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<ReservationData>> getAllReservations() {
        List<ReservationData> reservations = reservationDataService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ReservationData> getReservationById(@PathVariable Long id) {
        Optional<ReservationData> reservation = reservationDataService.getReservationById(id);
        return reservation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/number/{reservationNumber}")
    public ResponseEntity<ReservationData> getReservationByReservationNumber(@PathVariable String reservationNumber) {
        Optional<ReservationData> reservation = reservationDataService.getReservationByReservationNumber(reservationNumber);
        return reservation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationData> updateReservation(
            @PathVariable Long id, @RequestBody ReservationData reservationData) {
        ReservationData updatedReservation = reservationDataService.updateReservation(id, reservationData);
        return updatedReservation != null ?
                new ResponseEntity<>(updatedReservation, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        boolean isDeleted = reservationDataService.deleteReservation(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
