package com.example.moviereserve.entity.payment.card;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(nullable = false)
    private CardType cardType;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String cardExpiration;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String cardCVV;

    @DateTimeFormat(pattern = "YYYY-mm-dd HH:mm:ss")
    private LocalDateTime localDateTime;

    @PrePersist
    public void createTime(){
        this.localDateTime = LocalDateTime.now();
    }


    public CardRegister(User user, CardType cardType, String cardNumber, String cardExpiration, String password, String cardCVV){
        this.user = user;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.cardExpiration = cardExpiration;
        this.password = password;
        this.cardCVV = cardCVV;
    }
}
