package com.example.user.domain.response;

import com.example.user.domain.entity.Interest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class MeResponse {
    private String name;
    private String email;
    private String mbti;

    private List<Interest> interest;
}
