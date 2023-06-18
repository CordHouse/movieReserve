package com.example.moviereserve.service.seat;

import com.example.moviereserve.dto.seat.SeatInfo;
import com.example.moviereserve.dto.seat.SeatInfoResponseDto;
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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatReserveService {
    private static final String RESERVED = "occupied";
    private final SeatRepository seatRepository;
    private final VenuesRepository venuesRepository;

    // 좌석 예매
    @Transactional
    public SeatReserveResponseDto reserve(SeatReserveRequestDto seatReserveRequestDto, User user) {
        Venues venues = venuesRepository.findById(seatReserveRequestDto.getPerformanceId()).orElseThrow();
        List<SeatInfoResponseDto> seatInfoResponseDtoList = new ArrayList<>();
        for(SeatInfo reserve : seatReserveRequestDto.getSeats()) {
            Seat seat = seatRepository.findBySeatNumber(reserve.getSeatNumber()).orElseThrow();
            seat.setVenues(venues);
            seat.setUser(user);
            seat.setStatus(RESERVED);

            seatInfoResponseDtoList.add(new SeatInfoResponseDto().toDo(seat));
        }
        return new SeatReserveResponseDto().toDo(venues, seatInfoResponseDtoList);
    }
}
