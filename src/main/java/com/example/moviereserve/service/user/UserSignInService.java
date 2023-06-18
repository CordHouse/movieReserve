package com.example.moviereserve.service.user;

import com.example.moviereserve.config.jwt.TokenProvider;
import com.example.moviereserve.dto.sign.SignInRequestDto;
import com.example.moviereserve.dto.sign.UserSignInResponseDto;
import com.example.moviereserve.entity.user.User;
import com.example.moviereserve.exception.LoginFailureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserSignInService {
    private final BCryptPasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    // 로그인
    @Transactional(readOnly = true)
    public UserSignInResponseDto signIn(SignInRequestDto signInRequestDto, User user) {
        if(!passwordEncoder.matches(signInRequestDto.getPassword(), user.getPassword())) {
            throw new LoginFailureException();
        }

        Authentication authentication = getAuthentication(user);

        return new UserSignInResponseDto(tokenProvider.createToken(authentication));
    }

    /**
     * 유저 정보 확인
     */
    private UsernamePasswordAuthenticationToken getAuthentication(User user) {
        return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleUserGrade().toString())));
    }
}
