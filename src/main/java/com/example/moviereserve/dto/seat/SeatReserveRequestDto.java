package com.example.moviereserve.dto.seat;

import com.example.moviereserve.dto.payment.PaymentInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatReserveRequestDto {
    @NotNull(message = "공연 정보가 누락 되었습니다.")
    private long performanceId;
    @NotEmpty(message = "예매할 좌석이 누락 되었습니다.")
    private List<SeatInfo> seats;
    @NotNull(message = "총 가격이 누락 되었습니다.")
    private int totalPrice;
    private PaymentInfo paymentInfo;
}
