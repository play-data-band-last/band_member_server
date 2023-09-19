package com.example.user.api;

import com.example.user.domain.request.AlbumUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("BAND-CHATTING-SERVICE")
public interface ChattingClient {
    @PutMapping("/api/v1/chattings/memberupdate/{memberId}")
    void updateMember(@PathVariable("memberId")Long memberId,
                           @RequestBody AlbumUpdateRequest albumUpdateRequest);
}
