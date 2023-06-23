package com.example.moviereserve.dto.performance;

import com.example.moviereserve.dto.performance.price.PriceResponseDto;
import com.example.moviereserve.entity.performance.Performance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceInfo {
    private long performanceId;
    private String title;
    private String date;
    private String time;
    private String venue;
    private PriceResponseDto ticketPrice;
    private int availableSeats;

    public PerformanceInfo toDo(Performance performance) {
        return new PerformanceInfo(
                performance.getId(),
                performance.getName(),
                performance.getDate(),
                performance.getStartTime() + " ~ " + performance.getEndTime(),
                performance.getVenues().getVenuesName(),
                new PriceResponseDto().toDo(performance.getPrice()), // 가격 표시 해야함
                performance.getStatus()
        );
    }
}
