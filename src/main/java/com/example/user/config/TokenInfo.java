package com.example.user.config;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class TokenInfo {
    private Long id;
    private String name;
    private String email;
    private String mbti;
}
