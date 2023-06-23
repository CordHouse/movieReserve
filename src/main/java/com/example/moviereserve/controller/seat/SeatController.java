package com.example.moviereserve.controller.seat;

import com.example.moviereserve.dto.seat.SeatReserveRequestDto;
import com.example.moviereserve.dto.seat.SeatReserveResponseDto;
import com.example.moviereserve.entity.user.User;
import com.example.moviereserve.exception.NotFoundUserException;
import com.example.moviereserve.repository.UserRepository;
import com.example.moviereserve.service.seat.SeatReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SeatController {
    private final SeatReserveService seatReserveService;
    private final UserRepository userRepository;

    // 좌석 예매
    @PostMapping("/bookings")
    @ResponseStatus(HttpStatus.OK)
    public SeatReserveResponseDto reserve(@RequestBody @Valid SeatReserveRequestDto seatReserveRequestDto) {
        return seatReserveService.reserve(seatReserveRequestDto, getUser());
    }

    // 유저 토큰 정보를 기반으로 유저 정보 가져오기
    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName()).orElseThrow(NotFoundUserException::new);
    }
}
