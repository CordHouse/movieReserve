package com.example.moviereserve.controller;

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
    private static final UserService userService;

    @PostMapping("/businesses")
    @ResponseStatus(HttpStatus.OK)
    private Response businessSignUp(@RequestBody @Valid BusinessSignUpDto businessSignUpDto) {
        return Response.success(userService.businessSignUp(businessSignUpDto));
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    private Response userSignUp(@RequestBody @Valid UserSignUpDto userSignUpDto) {
        return Response.success(userService.userSignUpDto(userSignUpDto));
    }
}
