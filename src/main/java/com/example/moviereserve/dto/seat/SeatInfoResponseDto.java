package com.example.moviereserve.dto.seat;

import com.example.moviereserve.entity.seat.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatInfoResponseDto {
    private long seatId;
    private String seatNumber;
    private String status;

    public SeatInfoResponseDto toDo(Seat seat) {
        return new SeatInfoResponseDto(seat.getId(), seat.getSeatNumber(), seat.getStatus());
    }
}
