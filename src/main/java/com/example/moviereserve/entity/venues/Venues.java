package com.example.moviereserve.entity.venues;

import com.example.moviereserve.entity.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(nullable = false)
    private VenuesType venuesType; // 공연장 형태 -> 서서 보기, 앉아서 보기

    @Column(nullable = false)
    private String venuesStart;

    @Column(nullable = false)
    private String venuesEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @DateTimeFormat(pattern = "yyyy-mm-dd") // 공연 등록 일시
    private LocalDate createDate;

    @PrePersist // DB에 Insert 되기 직전에 실행된다.
    public void createDate(){
        this.createDate = LocalDate.now();
    }

    public Venues(String venuesName, int capacity, VenuesType venuesType, String venuesStart, String venuesEnd){
        this.venuesName = venuesName;
        this.capacity = capacity;
        this.venuesType = venuesType;
        this.venuesStart = venuesStart;
        this.venuesEnd = venuesEnd;
    }
}
