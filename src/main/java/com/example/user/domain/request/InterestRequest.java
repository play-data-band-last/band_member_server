package com.example.user.domain.request;

import com.example.user.domain.entity.Interest;
import com.example.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class InterestRequest {

    public String interest;

    public Interest toEntity(Long userId){
        return Interest
                .builder()
                .user(User
                        .builder()
                        .id(userId)
                        .build())
                .interest(interest)
                .build();
    }
}
