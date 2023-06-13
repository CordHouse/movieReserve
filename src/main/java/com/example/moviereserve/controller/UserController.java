package com.example.moviereserve.controller;

import com.example.moviereserve.dto.BusinessSignUpRequestDto;
import com.example.moviereserve.dto.SignInRequestDto;
import com.example.moviereserve.dto.UserSignUpRequestDto;
import com.example.moviereserve.entity.User;
import com.example.moviereserve.exception.LoginFailureException;
import com.example.moviereserve.repository.UserRepository;
import com.example.moviereserve.response.Response;
import com.example.moviereserve.service.UserSignInService;
import com.example.moviereserve.service.UserSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserSignInService userSignInService;
    private final UserSignUpService userSignUpService;
    private final UserRepository userRepository;

    /**
     * 사업자 회원가입
     */
    @PostMapping("/businesses")
    @ResponseStatus(HttpStatus.OK)
    private Response businessSignUp(@RequestBody @Valid BusinessSignUpRequestDto businessSignUpRequestDto) {
        return Response.success(userSignUpService.businessSignUp(businessSignUpRequestDto));
    }

    /**
     * 사용자 회원가입
     */
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    private Response userSignUp(@RequestBody @Valid UserSignUpRequestDto userSignUpRequestDto) {
        return Response.success(userSignUpService.userSignUp(userSignUpRequestDto));
    }

    /**
     * 로그인
     */
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    private Response signIn(@RequestBody @Valid SignInRequestDto requestDto) {
        User user = userRepository.findByName(requestDto.getName())
                .orElseThrow(LoginFailureException::new);
        return Response.success(userSignInService.signIn(requestDto, user));
    }
}
