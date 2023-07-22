package com.example.roomservice.repository;

import com.example.roomservice.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoomRepository extends JpaRepository<Room,Long> {}
