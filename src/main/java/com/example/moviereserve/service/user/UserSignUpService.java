package com.example.moviereserve.service.user;

import com.example.moviereserve.dto.sign.BusinessSignUpRequestDto;
import com.example.moviereserve.dto.sign.UserSignUpRequestDto;
import com.example.moviereserve.entity.user.RoleUserGrade;
import com.example.moviereserve.entity.user.User;
import com.example.moviereserve.exception.DuplicateEmailException;
import com.example.moviereserve.exception.DuplicateLicenseException;
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

    // 사업자 회원가입
    @Transactional
    public String businessSignUp(BusinessSignUpRequestDto businessSignUpRequestDto) {
        // 이메일 & 라이센스 중복체크
        emailAndLicenseDuplicateCheck(businessSignUpRequestDto.getEmail(),
                businessSignUpRequestDto.getBusinessLicense());

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

    // 사용자 회원가입
    @Transactional
    public String userSignUp(UserSignUpRequestDto userSignUpRequestDto) {
        // 이메일 중복체크
        emailAndLicenseDuplicateCheck(userSignUpRequestDto.getEmail(),
                null);

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

    // 이메일 & 라이센스 중복체크
    private void emailAndLicenseDuplicateCheck(String email, String license) {
        userRepository.findByEmailOrBusinessLicense(email, license).ifPresent(findUser -> {
            if(findUser.getEmail().equals(email)) {
                throw new DuplicateEmailException();
            }
            throw new DuplicateLicenseException();
        });
    }
}
