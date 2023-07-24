package com.example.reservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReservationResponse {
    private String reservationNumber;
    private Long roomId;
    private String name;
    private Date bookingDate;
}
