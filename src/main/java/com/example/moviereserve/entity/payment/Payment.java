package com.example.moviereserve.entity.payment;

import com.example.moviereserve.entity.payment.card.CardRegister;
import com.example.moviereserve.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "card_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CardRegister card;

    @Column(nullable = false)
    private String price;

    public Payment(String price) {
        this.price = price;
    }
}
