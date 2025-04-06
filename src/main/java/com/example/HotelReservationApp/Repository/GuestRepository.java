// src/main/java/com/example/demo/repository/UserRepository.java
package com.example.HotelReservationApp.Repository;

import com.example.HotelReservationApp.Entity.AdditionalGuest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<AdditionalGuest, Integer> {
}
