package com.example.user.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReserveUpdateRequest {
    private String communityName;
    private String communityImgPath;
}