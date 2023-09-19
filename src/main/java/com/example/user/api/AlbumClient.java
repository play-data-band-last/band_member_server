package com.example.user.api;

import com.example.user.domain.request.AlbumUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("BAND-ALBUM-SERVICE")
public interface AlbumClient {
    @PutMapping("api/v1/album/memberid/{memberId}")
    void memberUpdateInAlbum(
            @PathVariable("memberId") Long memberId,
            @RequestBody AlbumUpdateRequest request
    );
}
