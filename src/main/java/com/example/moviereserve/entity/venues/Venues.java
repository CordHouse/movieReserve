package com.example.moviereserve.entity.venues;

import com.example.moviereserve.entity.seat.Seat;
import com.example.moviereserve.entity.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Venues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String venuesName;

    @Column(nullable = false)
    private int capacity; // 수용량

    @Enumerated(EnumType.STRING)
    private VenuesType venuesType; // 공연장 형태 -> 서서 보기, 앉아서 보기

    @Column(nullable = false)
    private String possibleTimes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToMany(mappedBy = "venues", fetch = FetchType.LAZY)
    private List<Seat> seat;

    @DateTimeFormat(pattern = "yyyy-mm-dd") // 공연 등록 일시
    private LocalDate createDate;

    @PrePersist // DB에 Insert 되기 직전에 실행된다.
    public void createDate(){
        this.createDate = LocalDate.now();
    }

    public Venues(String venuesName, int capacity, VenuesType venuesType, String possibleTimes, User user){
        this.venuesName = venuesName;
        this.capacity = capacity;
        this.venuesType = venuesType;
        this.possibleTimes = possibleTimes;
        this.user = user;
    }
}
