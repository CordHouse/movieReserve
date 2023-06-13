package com.example.moviereserve.service;

import com.example.moviereserve.entity.RoleUserGrade;
import com.example.moviereserve.entity.User;
import com.example.moviereserve.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
public class UserSignUpService {
    private final UserRepository userRepository;

    @Transactional
    public BusinessSignUpResponseDto businessSignUp(BusinessSignUpRequestDto businessSignUpRequestDto) {
        User user = new User(
                businessSignUpRequestDto.getName(),
                passwordEncoder.encode(businessSignUpRequestDto.getPassword()),
                businessSignUpRequestDto.getEmail(),
                businessSignUpRequestDto.getBusinessLicense(),
                RoleUserGrade.findUserRole(businessLicense.getType()));

        userRepository.save(user);
        return new BusinessSignUpResponseDto(user);
    }

    @Transactional
    public UserSignUpResponseDto userSignUp(UserSignUpRequestDto userSignUpRequestDto) {
        User user = new User(
                userSignUpRequestDto.getName(),
                passwordEncoder.encode(userSignUpRequestDto.getPassword()),
                userSignUpRequestDto.getEmail(),
                RoleUserGrade.ROLE_COMMON_MEMBER);

        userRepository.save(user);
        return new UserSignUpResponseDto(user);
    }
}
