package com.example.user.domain.response;

import com.example.user.domain.entity.Reserve;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReserveResponse {
    private Long userId;
    private Long communityId;
    private String communityName;
    private String communityImgPath;

    public ReserveResponse(Reserve reserve) {
        this.userId = reserve.getUserId();
        this.communityId = reserve.getCommunityId();
        this.communityName = reserve.getCommunityName();
        this.communityImgPath = reserve.getCommunityImgPath();
    }
}