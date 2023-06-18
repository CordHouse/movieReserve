package com.example.moviereserve.dto.seat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatInfo {
    private long seatId;
    private String seatNumber;
}
