package com.example.user.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) @Getter
@Builder
@Table(name = "delete_users",
        indexes = @Index(name = "user_email", columnList = "email"))
public class DeleteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String mbti;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    //interest 반점 기준으로 반정규화해서 저장
    private String serealized_interest;
    private String imgPath;

//
//    @OneToMany(mappedBy = "deleteUser")
//    private List<Interest> interest;
}
