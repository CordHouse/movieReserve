package com.example.moviereserve.service.seat;

import com.example.moviereserve.entity.user.User;
import com.example.moviereserve.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeatReserveService {
    private final SeatRepository seatRepository;

    @Transactional
    public SeatReserveResponseDto reserve(SeatReserveRequestDto seatReserveRequestDto, User user) {

        return new SeatReserveResponseDto.toDo();
    }
}
