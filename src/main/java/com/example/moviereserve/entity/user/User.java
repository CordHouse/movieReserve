package com.example.moviereserve.entity.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

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
