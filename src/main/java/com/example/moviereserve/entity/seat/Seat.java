package com.example.moviereserve.entity.seat;

import com.example.moviereserve.entity.performance.Performance;
import com.example.moviereserve.entity.user.User;
import com.example.moviereserve.entity.venues.Venues;
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
public class Seat {
    private static final String DEFAULT = "available";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private String seatType;

    @Column(nullable = false)
    private String status;

    // 유저 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    // 공연 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venues_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Venues venues;

    // 예매 시간
    @DateTimeFormat(pattern = "YYYY-mm-dd HH:mm:ss")
    private LocalDateTime createDate;
    @PrePersist // DB에 Insert 되기 직전에 실행된다.
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

    public Seat(String seatNumber, String seatType, User user, Venues venues) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.user = user;
        this.venues = venues;
        this.status = DEFAULT;
    }
}
