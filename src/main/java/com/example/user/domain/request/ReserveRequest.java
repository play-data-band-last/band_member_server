package com.example.user.domain.request;

import com.example.user.domain.entity.Reserve;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class ReserveRequest {

    private Long communityId;
    private String communityName;
    private String communityImgPath;

    public Reserve toEntity(Long userId){
        return Reserve
                .builder()
                .userId(userId)
                .communityName(communityName)
                .communityImgPath(communityImgPath)
                .communityId(communityId)
                .build();
    }
}
