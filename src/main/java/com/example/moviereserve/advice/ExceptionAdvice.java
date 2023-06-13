package com.example.moviereserve.advice;

import com.example.moviereserve.exception.LoginFailureException;
import com.example.moviereserve.exception.NotFoundUserException;
import com.example.moviereserve.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response notFoundUserException() {
        return Response.failure(400, "유저를 찾을 수 없습니다.");
    }

    @ExceptionHandler(LoginFailureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response loginFailureException() {
        return Response.failure(400, "로그인이 실패하였습니다.");
    }
}
