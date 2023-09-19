package com.example.user.domain.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED) @Getter @Builder
@Table(name = "reserves")
public class Reserve {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long communityId;
    private String communityName;
    private String communityImgPath;

}