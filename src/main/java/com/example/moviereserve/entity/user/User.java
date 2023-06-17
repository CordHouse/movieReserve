package com.example.moviereserve.entity.user;

import com.example.moviereserve.entity.payment.card.CardRegister;
import com.example.moviereserve.entity.seat.Seat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 5)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String businessLicense;

    @Enumerated(EnumType.STRING)
    private RoleUserGrade roleUserGrade;

    // 좌석 연관관계
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Seat> seats;

    // 결제 연관관계
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CardRegister> cards;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate;

    @PrePersist // DB에 Insert 되기 직전에 실행된다.
    public void createDate(){
        this.createDate = LocalDate.now();
    }

    public User(String name, String password, String email, String businessLicense, RoleUserGrade roleUserGrade){
        this.name = name;
        this.password = password;
        this.email = email;
        this.businessLicense = businessLicense;
        this.roleUserGrade = roleUserGrade;
    }
}
