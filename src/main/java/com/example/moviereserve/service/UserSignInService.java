package com.example.moviereserve.service;

import com.example.moviereserve.config.jwt.TokenProvider;
import com.example.moviereserve.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignInService {
    private final BCryptPasswordEncoder passwordEncoder;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final TokenProvider tokenProvider;

    @Transactional
    public UserSignInResponseDto signIn(SignInRequestDto signInRequestDto, User user) {
        if(!passwordEncoder.matches(signInRequestDto.getPassword(), user.getPassword())) {
            throw new LoginFailureException();
        }

        UsernamePasswordAuthenticationToken authenticationToken = signInRequestDto.getAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return new UserSignInResponseDto(tokenProvider.createToken(authentication));
    }
}
