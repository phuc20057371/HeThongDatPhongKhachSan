package com.example.reservationservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "t_reservation")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    private String reservationNumber;
    private Long roomId;
    private String email;
    private Date bookingDate;

}
