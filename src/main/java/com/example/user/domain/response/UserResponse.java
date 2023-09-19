package com.example.user.domain.response;

import com.example.user.domain.dto.InterestDto;
import com.example.user.domain.entity.Interest;
import com.example.user.domain.entity.User;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Builder
@Setter @AllArgsConstructor @NoArgsConstructor
public class UserResponse {
    private String name;
    private String email;
    private String mbti;
    private List<InterestDto> interest;

    public UserResponse(User u){
        this.name=u.getName();
        this.email=u.getEmail();
        this.mbti=u.getMbti();
        this.interest=u.getInterest().stream()
                .map(interest1 -> new InterestDto(interest1))
                .toList();
    }

}
