// src/main/java/com/example/demo/controller/UserController.java
package com.example.HotelReservationApp.Controller;

import com.example.HotelReservationApp.Entity.ReservationData;
import com.example.HotelReservationApp.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController {

    @Autowired
    private ReservationRepository userRepository;

    @GetMapping("/user")
    public ReservationData getUser() {
        // For simplicity, return a hardcoded user or retrieve from DB
        ReservationData user = new ReservationData("John Doe", "john.doe@example.com");
        return user;
    }
}

