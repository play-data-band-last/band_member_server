package com.example.user.domain.dto;

import com.example.user.domain.entity.Interest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InterestDto {
    private Long id;
    private String interest;

    public InterestDto(Interest interest){
        this.id= interest.getId();
        this.interest = interest.getInterest();
    }
}
