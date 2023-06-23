package com.example.moviereserve.dto.performance;

import com.example.moviereserve.dto.performance.price.PriceResponseDto;
import com.example.moviereserve.entity.performance.Performance;
import com.example.moviereserve.entity.performance.price.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceResponseDto {
    private long venueId;
    private String name;
    private int capacity;
    private String date;
    private String startTime;
    private String endTime;
    private PriceResponseDto prices;

    public PerformanceResponseDto toDo(Performance performance, Price price) {
        return new PerformanceResponseDto(
                performance.getVenues().getId(),
                performance.getName(),
                performance.getCapacity(),
                performance.getDate(),
                performance.getStartTime(),
                performance.getEndTime(),
                new PriceResponseDto().toDo(price)
        );
    }
}
