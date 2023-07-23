package com.example.reservationservice.controller;

import com.example.reservationservice.dto.ReservationRequest;
import com.example.reservationservice.dto.RoomResponse;
import com.example.reservationservice.model.Reservation;
import com.example.reservationservice.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String bookingRoom(@RequestBody ReservationRequest request){
       return reservationService.bookingRoom(request);
 //       return "Booking room Successfully";
 //       return new RoomResponse();
    }
}
