package com.example.moviereserve.dto.seat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatReserveRequestDto {
    @NotNull(message = "공연 정보가 누락 되었습니다.")
    private long performanceId;
    @NotBlank(message = "예매할 좌석이 누락 되었습니다.")
    private List<SeatInfo> seats;
}

class SeatInfo{
    private long seatId;
    private String seatNumber;
}
