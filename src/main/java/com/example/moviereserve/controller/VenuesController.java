package com.example.moviereserve.controller;

import com.example.moviereserve.dto.venues.VenuesResponseDto;
import com.example.moviereserve.dto.venues.VenuesSetupRequestDto;
import com.example.moviereserve.entity.user.User;
import com.example.moviereserve.exception.NotFoundUserException;
import com.example.moviereserve.repository.UserRepository;
import com.example.moviereserve.service.VenuesService;
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
public class VenuesController {
    private final VenuesService venuesService;
    private final UserRepository userRepository;

    // 공연장 등록 -> 사업자만 가능
    // 일반 유저는 접근 못하게 url 설정
    @PostMapping("/venues")
    @ResponseStatus(HttpStatus.OK)
    private VenuesResponseDto venuesSetup(@RequestBody @Valid VenuesSetupRequestDto venuesSetupRequestDto) {
        getUser();
        return venuesService.venuesSetup(venuesSetupRequestDto);
    }

    // 유저 토큰 정보를 기반으로 유저 정보 가져오기
    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName()).orElseThrow(NotFoundUserException::new);
    }
}
