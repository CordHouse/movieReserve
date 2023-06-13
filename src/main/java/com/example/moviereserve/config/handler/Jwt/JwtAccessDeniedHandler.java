package com.example.moviereserve.config.handler.Jwt;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// JWT 관련 오류 처리하기 위한 클래스
// AuthenticationEntryPoint와의 차이점은 Authentication은 존재하나, 접근 권한이 없는 경우 에러 발생
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // code : 403 -> 접근권한이 없는 경우 발생
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}
