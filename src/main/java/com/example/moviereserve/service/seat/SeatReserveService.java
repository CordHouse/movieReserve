package com.example.moviereserve.service.seat;

import com.example.moviereserve.dto.payment.PaymentInfo;
import com.example.moviereserve.dto.seat.SeatInfo;
import com.example.moviereserve.dto.seat.SeatInfoResponseDto;
import com.example.moviereserve.dto.seat.SeatReserveRequestDto;
import com.example.moviereserve.dto.seat.SeatReserveResponseDto;
import com.example.moviereserve.entity.payment.Payment;
import com.example.moviereserve.entity.performance.Performance;
import com.example.moviereserve.entity.seat.Seat;
import com.example.moviereserve.entity.user.User;
import com.example.moviereserve.exception.NotFoundPerformanceException;
import com.example.moviereserve.exception.SeatReserveException;
import com.example.moviereserve.exception.SeatReservedException;
import com.example.moviereserve.repository.PaymentRepository;
import com.example.moviereserve.repository.PerformanceRepository;
import com.example.moviereserve.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatReserveService {
    private static final String RESERVED = "occupied";
    private final SeatRepository seatRepository;
    private final PerformanceRepository performanceRepository;
    private final PaymentRepository paymentRepository;

    // 좌석 예매
    @Transactional
    public SeatReserveResponseDto reserve(SeatReserveRequestDto seatReserveRequestDto, User user) {
        Performance performance = performanceRepository.findById(seatReserveRequestDto.getPerformanceId()).orElseThrow(() -> {
            throw new NotFoundPerformanceException();
        });
        if(performance.getStatus() == 0) {
            throw new SeatReserveException(); // 좌석이 다 찼습니다.
        }

        List<SeatInfoResponseDto> seatInfoResponseDtoList = new ArrayList<>();
        for(SeatInfo reserve : seatReserveRequestDto.getSeats()) {
            Seat seat = seatRepository.findByIdAndSeatNumberAndVenues(reserve.getSeatId(), reserve.getSeatNumber(), performance.getVenues()).orElseThrow(() -> {
                throw new SeatReserveException(); // 예약할 수 없는 좌석
            });
            if(seat.getStatus().equals(RESERVED)) {
                throw new SeatReservedException(); // 이미 예약된 좌석
            }
            seat.setUser(user);
            seat.setStatus(RESERVED);

            seatInfoResponseDtoList.add(new SeatInfoResponseDto().toDo(seat));
        }

        doPayment(seatReserveRequestDto.getPaymentInfo(), seatReserveRequestDto.getTotalPrice(), user);

        // 예약한 좌석 수 만큼 제외하기
        if(seatInfoResponseDtoList.size() > 0) {
            performance.setStatus(performance.getStatus() - seatInfoResponseDtoList.size());
        }
        return new SeatReserveResponseDto().toDo(performance, seatInfoResponseDtoList);
    }

    @Transactional
    public void doPayment(PaymentInfo paymentInfo, int totalPrice, User user) {
        Payment payment = new Payment(
                user,
                paymentInfo.getPaymentMethod(),
                paymentInfo.getCardNumber(),
                paymentInfo.getCardExpiration(),
                paymentInfo.getCardCVV(),
                totalPrice
        );
        paymentRepository.save(payment);
    }
}