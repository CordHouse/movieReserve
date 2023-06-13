package com.example.moviereserve.config.filter;

import com.example.moviereserve.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Spring Security 를 적용하면 Filter Chain 에 의해 인증 절차가 진행된다.
// 하지만 Spring Security 에서는 Jwt 에 대한 Filter 를 제공하지 않는다. -> 따라서 커스텀 추가해줘야한다.
// OncePerRequestFilter 상속받아 Jwt 필터를 만들어준다.
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    private static final String AUTHENTICATION_KEY = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = resolveToken(request);

        if(StringUtils.hasText(jwtToken) && tokenProvider.validationToken(jwtToken)) {
            Authentication authentication = tokenProvider.getAuthentication(jwtToken);
            // authentication 정보를 SecurityContextHolder 에 담는다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // Security Filter chain 에 등록
        filterChain.doFilter(request, response);
    }

    public String resolveToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(AUTHENTICATION_KEY);

        if(StringUtils.hasText(jwtToken) && jwtToken.startsWith("Bearer ")) {
            return jwtToken.substring(7);
        }
        return null;
    }
}
