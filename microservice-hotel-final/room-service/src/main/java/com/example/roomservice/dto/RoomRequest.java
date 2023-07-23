package com.example.roomservice.dto;

import com.example.roomservice.model.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RoomRequest {
    private Type type;
    private int capacity;
    private double price;
    private boolean isAvailable;
}
