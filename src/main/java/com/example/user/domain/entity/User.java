package com.example.user.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity @AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED) @Getter @Builder
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String mbti;
    private LocalDateTime createdAt;
    private String imgPath;

    @OneToMany(mappedBy = "user")
    private List<Interest> interest;


}
