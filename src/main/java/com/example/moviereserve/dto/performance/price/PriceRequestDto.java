package com.example.moviereserve.dto.performance.price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequestDto {
    @NotNull(message = "VIP 금액을 입력해주세요.")
    private int vip;
    @NotNull(message = "일반 금액을 입력해주세요.")
    private int common;
}
