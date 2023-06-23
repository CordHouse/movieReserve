package com.example.moviereserve.controller.performance;

import com.example.moviereserve.dto.performance.PerformanceInfoResponseDto;
import com.example.moviereserve.dto.performance.PerformanceRequestDto;
import com.example.moviereserve.dto.performance.PerformanceResponseDto;
import com.example.moviereserve.dto.performance.PerformanceSeatsInfoResponseDto;
import com.example.moviereserve.entity.user.User;
import com.example.moviereserve.exception.NotFoundUserException;
import com.example.moviereserve.repository.UserRepository;
import com.example.moviereserve.service.performance.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/performances")
public class PerformanceController {
    private final PerformanceService performanceService;
    private final UserRepository userRepository;

    // 공연 등록
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public PerformanceResponseDto register(@RequestBody @Valid PerformanceRequestDto performanceRequestDto) {
        return performanceService.register(performanceRequestDto, getUser());
    }

    // 공연 정보 조회
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public PerformanceInfoResponseDto getPerformanceInfo() {
        return performanceService.getPerformanceInfo();
    }

    // 공연 잔여 좌석 조회
    @GetMapping("/{performanceId}/seats")
    @ResponseStatus(HttpStatus.OK)
    public PerformanceSeatsInfoResponseDto getPerformanceSeatsInfo(@PathVariable long performanceId) {
        return performanceService.getPerformanceSeatsInfo(performanceId);
    }

    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName()).orElseThrow(NotFoundUserException::new);
    }
}
