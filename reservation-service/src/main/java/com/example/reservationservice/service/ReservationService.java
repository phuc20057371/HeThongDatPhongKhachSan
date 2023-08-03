package com.example.reservationservice.service;

import com.example.reservationservice.dto.ReservationRequest;
import com.example.reservationservice.dto.RoomResponse;
import com.example.reservationservice.dto.UserResponse;
import com.example.reservationservice.model.Reservation;
import com.example.reservationservice.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final WebClient.Builder builder;
    public String bookingRoom(ReservationRequest request){
        Reservation reservation = new Reservation();
        reservation.setReservationNumber(UUID.randomUUID().toString());

        UserResponse userResponse = builder.build().get()
                .uri("http://security-service/auth/find/"+request.getName())
                .retrieve().bodyToMono(UserResponse.class)
                .block();
        reservation.setName(userResponse.getName());

        RoomResponse roomResponse = builder.build().get()
                .uri("http://room-service/room/"+request.getRoomId().toString())
                .retrieve().bodyToMono(RoomResponse.class)
                .block();
//        List<Integer> list = List.of(Math.toIntExact(request.getRoomId()));
//        RoomResponse[] roomResponse = builder.build().get()
//                .uri("http://room-service/api/v1/room",
//                        uriBuilder -> uriBuilder.queryParam("id", list).build())
//                .retrieve()
//                .bodyToMono(RoomResponse[].class)
//                .block();
 //       return roomResponse;
//        String uri = "http://localhost:8081/api/v1/room/"+request.getRoomId().toString();
//        RestTemplate restTemplate = new RestTemplate();
//        RoomResponse roomResponse = restTemplate.getForObject(uri, RoomResponse.class);
        if(roomResponse!=null&&roomResponse.isAvailable()){
            reservation.setRoomId(request.getRoomId());
            reservation.setBookingDate(new Date(System.currentTimeMillis()));
            reservationRepository.save(reservation);
            String respone = builder.build().post().uri("http://room-service/room/update/" + roomResponse.getId())
                    .retrieve().bodyToMono(String.class).block();
            //return  "Booking room successfully!";
            return respone;
        }
        return  "Fail! This room is booked!";
    }

}
