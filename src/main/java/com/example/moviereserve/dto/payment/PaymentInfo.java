package com.example.moviereserve.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfo {
    @NotBlank(message = "결제 방법을 입력해주세요.")
    private String paymentMethod;
    @NotBlank(message = "카드번호를 4자리씩 띄워서 입력해주세요.")
    @Pattern(regexp = "\\d{4} \\d{4} \\d{4} \\d{4}")
    private String cardNumber;
    @NotBlank(message = "카드 만료기한을 작성해주세요 (월/년)")
    private String cardExpiration;
    @NotBlank(message = "카드 뒷면 CVV를 입력해주세요")
    @Size(min = 3, max = 3)
    private String cardCVV;
}
