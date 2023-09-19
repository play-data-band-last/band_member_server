package com.example.user.api;

import com.example.user.domain.request.AlbumUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("BAND-SCHEDULE-SERVICE")
public interface ScheduleClient {
    @PutMapping("/api/v1/schedule/updatemember/{memberId}")
    void updateMemberBoard(@PathVariable("memberId") Long memberId,
                           @RequestBody AlbumUpdateRequest albumUpdateRequest);
}
