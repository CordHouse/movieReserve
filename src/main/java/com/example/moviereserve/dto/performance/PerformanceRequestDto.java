package com.example.moviereserve.dto.performance;

import com.example.moviereserve.dto.performance.price.PriceRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceRequestDto {
    @NotNull(message = "공연장 id를 입력해주세요.")
    private long venueId;
    @NotBlank(message = "공연 이름 입력해주세요.")
    private String name;
    @NotNull(message = "수용량을 입력해주세요.")
    private int capacity;
    @NotBlank(message = "날짜를 입력해주세요.")
    private String date;
    @NotBlank(message = "시작 시간을 입력해주세요.")
    private String startTime;
    @NotBlank(message = "종료 시간을 입력해주세요.")
    private String endTime;
    private PriceRequestDto prices;
}
