package com.example.moviereserve.dto.performance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceInfoResponseDto {
    private List<PerformanceInfo> performances;

    public PerformanceInfoResponseDto toDo(List<PerformanceInfo> performances) {
        return new PerformanceInfoResponseDto(performances);
    }
}
