package com.example.moviereserve.dto.performance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceSeatsInfoResponseDto {
    private long performanceId;
    private List<PerformanceSeatInfo> seats;

    public PerformanceSeatsInfoResponseDto toDo(long performanceId, List<PerformanceSeatInfo> seats) {
        return new PerformanceSeatsInfoResponseDto(performanceId, seats);
    }
}
