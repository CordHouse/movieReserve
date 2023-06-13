package com.example.moviereserve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessSignUpRequestDto {
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotBlank(message = "사업자 등록번호를 입력해주세요.")
    private String businessLicense;
    @NotBlank(message = "역할을 입력해주세요.")
    private String type;
}
