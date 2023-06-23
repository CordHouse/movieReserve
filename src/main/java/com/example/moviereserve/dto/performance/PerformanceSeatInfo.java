package com.example.moviereserve.dto.performance;

import com.example.moviereserve.entity.seat.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceSeatInfo {
    private long seatId;
    private String seatNumber;
    private String status;

    public PerformanceSeatInfo toDo(Seat seat) {
        return new PerformanceSeatInfo(seat.getId(), seat.getSeatNumber(), seat.getStatus());
    }
}
