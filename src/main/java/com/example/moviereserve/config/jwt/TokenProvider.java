package com.example.moviereserve.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j // log 출력시 사용하기 위한 어노테이션
public class TokenProvider implements InitializingBean {
    private static final String AUTHENTICATION_KEY = "auth";
    private Key key;
    private String secret;
    private Long tokenValidationTime;
    private Long refreshTokenValidationTime;
    private String refresh_token;

    // build.gradle -> jwt 의존성 추가해주어야 가능하다.
    // secret은 보안되어야 하기 때문에 application.yml 에서 작성 후 적용
    // secret 정보로 key를 암호화하는 과정
    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] secret_key = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(secret_key);
    }

    // TokenProvider 생성자
    // 만료시간 또한 application.yml 정의
    // @Value 는 beans path로 선택
    // tokenValidationTime -> 3600으로 설정
    public TokenProvider(@Value("${jwt.tokenValidationTime}") long tokenValidationTime,
                         @Value("${jwt.secret}") String secret) {
        this.secret = secret;
        this.tokenValidationTime = tokenValidationTime * 2 * 1000;
        this.refreshTokenValidationTime = tokenValidationTime * 24 * 7 * 1000;
    }

    // 토큰 생성
    public TokenResponseDto createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities()
                .stream().map(s -> s.getAuthority()).collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        Date expirationTime = new Date(now + tokenValidationTime); // 토큰정보 만료시간
        Date refreshTokenExpirationTime = new Date(now + refreshTokenValidationTime); // 리프레시 토큰정보 만료시간

        String access_token = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(expirationTime)
                .claim(AUTHENTICATION_KEY, authorities) // 헤더에 담을 내용
                .signWith(this.key, SignatureAlgorithm.HS512)
                .compact();

        refresh_token = Jwts.builder()
                .setExpiration(refreshTokenExpirationTime)
                .signWith(this.key, SignatureAlgorithm.HS512)
                .compact();

        return new TokenResponseDto().toDo(access_token);
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    // 토큰을 통해 Authentication 객체 생성
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(this.key)
                .build()
                .parseClaimsJws(token) // jwt <-> jws 주의
                .getBody();

        List<SimpleGrantedAuthority> authorities = Arrays
                .stream(claims.get(AUTHENTICATION_KEY).toString().split(","))
                .map(s -> new SimpleGrantedAuthority(s))
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        // UsernamePasswordAuthenticationToken 사용시 맨 마지막은 권한부여가 있어야 하기 때문에 빈 리스트라도 필수로 넣어줘야한다. 안그러면 401떠요..
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // 토큰 검증
    public boolean validationToken(String token) {
        try {
            // 토큰이 만들어지면 검증된 토큰이다.
            Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token);
            return true;
        } catch(SecurityException | MalformedJwtException e) {
            log.info("잘못된 토큰입니다.");
        } catch(ExpiredJwtException e) {
            log.info("이미 만료된 토큰입니다.");
        } catch(UnsupportedJwtException e) {
            log.info("지원하지 않는 토큰입니다.");
        } catch(IllegalArgumentException e) {
            log.info("토큰이 잘못되었습니다.");
        }
        return false;
    }
}
