package com.example.moviereserve.dto.venues;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenuesSetupRequestDto {
    @NotBlank(message = "공연장 이름을 입력해주세요.")
    private String name;
    @NotNull(message = "수용할 좌석 크기를 입력해주세요.")
    private int capacity;
    @NotBlank(message = "공연장 형태를 입력해주세요. (FIXED_SEAT or STANDING)")
    private String venuesType;
    @NotBlank(message = "예매 시작 시간을 입력해주세요.")
    private String startTime;
    @NotBlank(message = "예매 종료 시간을 입력해주세요.")
    private String endTime;
    @NotNull(message = "vip로 설정할 좌석 크기를 입력해주세요. (1번 좌석부터 설정됩니다.)")
    private int vipSeatMaxNumber;
}
