package com.example.moviereserve.service.seat;

import com.example.moviereserve.dto.seat.SeatInfo;
import com.example.moviereserve.dto.seat.SeatReserveRequestDto;
import com.example.moviereserve.dto.seat.SeatReserveResponseDto;
import com.example.moviereserve.entity.seat.Seat;
import com.example.moviereserve.entity.user.User;
import com.example.moviereserve.entity.venues.Venues;
import com.example.moviereserve.repository.SeatRepository;
import com.example.moviereserve.repository.VenuesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeatReserveService {
    private static final String RESERVED = "occupied"
    private final SeatRepository seatRepository;
    private final VenuesRepository venuesRepository;

    // 좌석 예매
    @Transactional
    public SeatReserveResponseDto reserve(SeatReserveRequestDto seatReserveRequestDto, User user) {
        Venues venues = venuesRepository.findById(seatReserveRequestDto.getPerformanceId()).orElseThrow();
        for(SeatInfo reserve : seatReserveRequestDto.getSeats()) {
            Seat seat = seatRepository.findBySeatNumber(reserve.getSeatNumber()).orElseThrow();
            seat.setVenues(venues);
            seat.setUser(user);
            seat.setStatus(RESERVED);
        }
        return new SeatReserveResponseDto().toDo(venues, seatReserveRequestDto.getSeats());
    }
}
