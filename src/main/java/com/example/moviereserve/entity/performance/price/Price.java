package com.example.moviereserve.entity.performance.price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int vip;

    @Column(nullable = false)
    private int common;

    public Price(int vip, int common) {
        this.vip = vip;
        this.common = common;
    }
}
