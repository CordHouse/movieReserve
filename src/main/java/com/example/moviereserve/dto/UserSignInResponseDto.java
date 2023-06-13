package com.example.moviereserve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInResponseDto {
    private String originToken;

    public UserSignInResponseDto toDo(String token) {
        return new UserSignInResponseDto(token);
    }
}
