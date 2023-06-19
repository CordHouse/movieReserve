package com.example.moviereserve.dto.seat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatRegisterDto {
    private String seatNumber;
    private String seatType;
}
