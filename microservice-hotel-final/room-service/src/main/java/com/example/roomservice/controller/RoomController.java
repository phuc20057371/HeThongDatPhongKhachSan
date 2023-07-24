package com.example.roomservice.controller;

import com.example.roomservice.dto.RoomRequest;
import com.example.roomservice.dto.RoomResponse;
import com.example.roomservice.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createRoom (@RequestBody RoomRequest request){
        roomService.createRoom(request);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoomResponse> getAllRooms (){
        return roomService.getAllRooms();
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomResponse findRoom (@PathVariable("id") Long id){
        return roomService.findRoom(id);
    }
}
