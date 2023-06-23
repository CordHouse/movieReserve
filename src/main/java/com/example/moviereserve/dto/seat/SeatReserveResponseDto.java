package com.example.moviereserve.dto.seat;

import com.example.moviereserve.entity.performance.Performance;
import com.example.moviereserve.entity.seat.Seat;
import com.example.moviereserve.entity.venues.Venues;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatReserveResponseDto {
    private long performanceId;
    private List<SeatInfoResponseDto> seats;

    public SeatReserveResponseDto toDo(Performance performance, List<SeatInfoResponseDto> seat) {
        return new SeatReserveResponseDto(performance.getId(), seat);
    }
}
