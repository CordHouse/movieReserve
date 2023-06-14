package com.example.moviereserve.service;

import com.example.moviereserve.dto.BusinessSignUpRequestDto;
import com.example.moviereserve.dto.UserSignUpRequestDto;
import com.example.moviereserve.entity.RoleUserGrade;
import com.example.moviereserve.entity.User;
import com.example.moviereserve.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
public class UserSignUpService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final String SUCCESS = "회원가입 완료";
    private static final String NOT_BUSINESS = "-";

    @Transactional
    public String businessSignUp(BusinessSignUpRequestDto businessSignUpRequestDto) {
        User user = new User(
                businessSignUpRequestDto.getName(),
                passwordEncoder.encode(businessSignUpRequestDto.getPassword()),
                businessSignUpRequestDto.getEmail(),
                businessSignUpRequestDto.getBusinessLicense(),
                RoleUserGrade.findUserRole(businessSignUpRequestDto.getType())
        );

        userRepository.save(user);
        return SUCCESS;
    }

    @Transactional
    public String userSignUp(UserSignUpRequestDto userSignUpRequestDto) {
        User user = new User(
                userSignUpRequestDto.getName(),
                passwordEncoder.encode(userSignUpRequestDto.getPassword()),
                userSignUpRequestDto.getEmail(),
                NOT_BUSINESS,
                RoleUserGrade.ROLE_COMMON_MEMBER
        );

        userRepository.save(user);
        return SUCCESS;
    }
}
