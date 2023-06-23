package com.example.moviereserve.dto.performance.price;

import com.example.moviereserve.entity.performance.price.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceResponseDto {
    private int vip;
    private int common;

    public PriceResponseDto toDo(Price price) {
        return new PriceResponseDto(price.getVip(), price.getCommon());
    }
}
