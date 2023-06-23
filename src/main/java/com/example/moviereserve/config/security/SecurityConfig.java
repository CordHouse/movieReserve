package com.example.moviereserve.config.security;

import com.example.moviereserve.config.handler.Auth.AuthenticationEntryPointHandler;
import com.example.moviereserve.config.handler.Jwt.JwtAccessDeniedHandler;
import com.example.moviereserve.config.jwt.JwtSecurityConfig;
import com.example.moviereserve.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final AuthenticationEntryPointHandler AuthenticationEntryPointHandler;

    private static final String[] WHITE_LIST = {
            "/businesses",
            "/users",
            "/sign-in"
    };
    private static final String[] PERFORMANCE_WHITE_LIST = {
            "/performances/**"
    };

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(AuthenticationEntryPointHandler)

                .and()
                .logout().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, WHITE_LIST).permitAll()
                .antMatchers(HttpMethod.GET, PERFORMANCE_WHITE_LIST).permitAll()
                .antMatchers("/venues", "/performances").access("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_VENUE_MANAGER\")" +
                        " or hasRole(\"ROLE_PERFORMANCE_MANAGER\")")
                .antMatchers("/**").access("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_VENUE_MANAGER\")" +
                        " or hasRole(\"ROLE_PERFORMANCE_MANAGER\") or hasRole(\"ROLE_COMMON_MEMBER\")") // 화이트리스트 제외한 모든 경로는 유저 권한이 있어야함.
                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return httpSecurity.build();
    }
}
