package com.example.moviereserve.config.handler.Auth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component // 빈 등록
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    // HttpServletRequest, HttpServletResponse 이용하기 위해 web 의존성 추가
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // code : 401 -> 허가받지 않은 사용자
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
