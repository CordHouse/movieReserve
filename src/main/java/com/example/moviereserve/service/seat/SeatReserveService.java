package com.example.moviereserve.service.seat;

import com.example.moviereserve.dto.payment.PaymentInfo;
import com.example.moviereserve.dto.seat.SeatInfo;
import com.example.moviereserve.dto.seat.SeatInfoResponseDto;
import com.example.moviereserve.dto.seat.SeatReserveRequestDto;
import com.example.moviereserve.dto.seat.SeatReserveResponseDto;
import com.example.moviereserve.entity.payment.Payment;
import com.example.moviereserve.entity.seat.Seat;
import com.example.moviereserve.entity.user.User;
import com.example.moviereserve.entity.venues.Venues;
import com.example.moviereserve.repository.PaymentRepository;
import com.example.moviereserve.repository.SeatRepository;
import com.example.moviereserve.repository.VenuesRepository;
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
    private final VenuesRepository venuesRepository;
    private final PaymentRepository paymentRepository;

    // 좌석 예매
    @Transactional
    public SeatReserveResponseDto reserve(SeatReserveRequestDto seatReserveRequestDto, User user) {
        Venues venues = venuesRepository.findById(seatReserveRequestDto.getPerformanceId()).orElseThrow();
        List<SeatInfoResponseDto> seatInfoResponseDtoList = new ArrayList<>();
        for(SeatInfo reserve : seatReserveRequestDto.getSeats()) {
            Seat seat = seatRepository.findBySeatNumberAndVenues(reserve.getSeatNumber(), venues).orElseThrow();
            seat.setUser(user);
            seat.setStatus(RESERVED);

            seatInfoResponseDtoList.add(new SeatInfoResponseDto().toDo(seat));
        }

        doPayment(seatReserveRequestDto.getPaymentInfo(), seatReserveRequestDto.getTotalPrice(), user);
        return new SeatReserveResponseDto().toDo(venues, seatInfoResponseDtoList);
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