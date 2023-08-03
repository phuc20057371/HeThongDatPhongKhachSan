package com.example.roomservice.service;

import com.example.roomservice.dto.RoomRequest;
import com.example.roomservice.dto.RoomResponse;
import com.example.roomservice.model.Room;
import com.example.roomservice.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomService {
    private final RoomRepository roomRepository;
    public void createRoom(RoomRequest request){
        Room room = Room.builder()
                .type(request.getType())
                .capacity(request.getCapacity())
                .price(request.getPrice())
                .isAvailable(true)
                .build();
        roomRepository.save(room);
        log.info("Room {} is saved",room.getId());
    }
    public RoomResponse findRoom(Long id){
        Room room = roomRepository.findById(id).orElseThrow();
        return mapToRoomResponse(room);
    }
    public List<RoomResponse> getAllRooms() {
        List<Room>  products = roomRepository.findAll();
        return products.stream().map(this::mapToRoomResponse).toList();
    }
    public String updateRoom(Long id){
        Room room = roomRepository.findById(id).orElseThrow();
        room.setAvailable(false);
        roomRepository.save(room);
        return "room booked!";
    }

    private RoomResponse mapToRoomResponse(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .type(room.getType())
                .capacity(room.getCapacity())
                .price(room.getPrice())
                .isAvailable(room.isAvailable())
                .build();
    }

}
