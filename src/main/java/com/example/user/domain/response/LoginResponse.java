package com.example.user.domain.response;

import com.example.user.domain.entity.Interest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class LoginResponse {
    private String token;
    private String email;
    private String username;
    private String profileImgPath;
    private String mbti;
    private Long userId;
    private List<Interest> interests;
}
