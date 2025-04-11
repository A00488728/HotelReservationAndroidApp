// src/main/java/com/example/demo/service/ReservationDataService.java
package com.example.HotelReservationApp.Service;

import com.example.HotelReservationApp.Entity.ReservationData;
import com.example.HotelReservationApp.Repository.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationDataService {

    private final ReservationRepository reservationDataRepository;

    @Autowired
    public ReservationDataService(ReservationRepository reservationDataRepository) {
        this.reservationDataRepository = reservationDataRepository;
    }

    public ReservationData createReservation(ReservationData reservationData) {
        return reservationDataRepository.save(reservationData);
    }

    public List<ReservationData> getAllReservations() {
        return reservationDataRepository.findAll();
    }

    public Optional<ReservationData> getReservationById(Long id) {
        return reservationDataRepository.findById(id);
    }
    public Optional<ReservationData> getReservationByReservationNumber(String reservationNumber) {
        return reservationDataRepository.findByReservationNumber(reservationNumber);
    }    
    public ReservationData updateReservation(Long id, ReservationData reservationData) {
        if (reservationDataRepository.existsById(id)) {
            reservationData.setId(id);
            return reservationDataRepository.save(reservationData);
        } else {
            return null;
        }
    }

    public boolean deleteReservation(Long id) {
        if (reservationDataRepository.existsById(id)) {
            reservationDataRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
