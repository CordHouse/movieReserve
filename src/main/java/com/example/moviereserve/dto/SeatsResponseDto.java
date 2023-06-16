package com.example.moviereserve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeatsResponseDto {
    private String seatNumber;
    private String seatType;
}
