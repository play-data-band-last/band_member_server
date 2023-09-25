package com.example.user.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlbumUpdateRequest {
    private String memberName;
    private String memberImage;
    private Long memberId;
}
