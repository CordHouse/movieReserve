package com.example.moviereserve.dto.seat;

import com.example.moviereserve.entity.seat.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatReserveResponseDto {
    private long performanceId;
    private long seatId;
    private String seatNumber;

    public SeatReserveResponseDto toDo(Seat seat) {
        return new SeatReserveResponseDto(seat.getVenues().getId(), seat.getId(), seat.getSeatNumber());
    }
}
