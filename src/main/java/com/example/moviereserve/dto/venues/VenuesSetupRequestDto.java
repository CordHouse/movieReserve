package com.example.moviereserve.dto.venues;

import com.example.moviereserve.dto.seat.SeatRegisterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    @NotBlank(message = "운영 시간을 입력해주세요.")
    private String possibleTimes;
    @NotNull(message = "하나 이상 좌석을 등록해주세요.")
    private List<SeatRegisterDto> seats;
}
