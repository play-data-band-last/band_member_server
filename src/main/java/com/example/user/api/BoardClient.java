package com.example.user.api;

import com.example.user.domain.request.AlbumUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("BAND-BOARD-SERVICE")
public interface BoardClient {
    @PutMapping("/api/v1/board/updatemember/{memberId}")
    void updateMemberBoard(@PathVariable("memberId")Long memberId,
                           @RequestBody AlbumUpdateRequest albumUpdateRequest);
}
