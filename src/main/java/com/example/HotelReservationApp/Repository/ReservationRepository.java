// src/main/java/com/example/demo/repository/UserRepository.java
package com.example.HotelReservationApp.Repository;

import com.example.HotelReservationApp.Entity.ReservationData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationData, Long> {
	ReservationData findTopByOrderByIdDesc();
}
