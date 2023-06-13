package com.example.moviereserve.controller;

import com.example.moviereserve.entity.User;
import com.example.moviereserve.response.Response;
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
    private final UserService userService;

    /**
     * 사업자 회원가입
     */
    @PostMapping("/businesses")
    @ResponseStatus(HttpStatus.OK)
    private Response businessSignUp(@RequestBody @Valid BusinessSignUpRequestDto businessSignUpRequestDto) {
        return Response.success(userService.businessSignUp(businessSignUpDto));
    }

    /**
     * 사용자 회원가입
     */
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    private Response userSignUp(@RequestBody @Valid UserSignUpRequestDto userSignUpRequestDto) {
        return Response.success(userService.userSignUpRequestDto(userSignUpRequestDto));
    }

    /**
     * 로그인
     */
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public Response signIn(@RequestBody @Valid UserSignInRequestDto requestDto) {
        User user = userRepository.findByName(requestDto.getName())
                .orElseThrow(LoginFailureException::new);
        return Response.success(userService.signIn(requestDto, user));
    }
}
