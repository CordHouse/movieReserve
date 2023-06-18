package com.example.moviereserve.entity.payment;

import com.example.moviereserve.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String cardExpiration;

    @Column(nullable = false)
    private String cardCVV;

    @Column(nullable = false)
    private int totalPrice;

    @DateTimeFormat(pattern = "YYYY-mm-dd HH:mm:ss")
    private LocalDateTime localDateTime;

    @PrePersist
    public void createTime(){
        this.localDateTime = LocalDateTime.now();
    }

    public Payment(User user, String paymentMethod, String cardNumber, String cardExpiration, String cardCVV, int totalPrice){
        this.user = user;
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
        this.cardExpiration = cardExpiration;
        this.cardCVV = cardCVV;
        this.totalPrice = totalPrice;
    }
}
