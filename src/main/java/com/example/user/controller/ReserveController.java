package com.example.user.controller;

import com.example.user.domain.request.ReserveRequest;
import com.example.user.domain.request.ReserveUpdateRequest;
import com.example.user.domain.response.ReserveResponse;
import com.example.user.service.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bandMember/reserve")
@CrossOrigin("*")
public class ReserveController {
    private final ReserveService reserveService;
    @PostMapping("{userId}")
    public void save(@PathVariable Long userId, @RequestBody ReserveRequest request){
        reserveService.save(userId, request);
    }

    @GetMapping("{userId}")
    public List<ReserveResponse> getAll(@PathVariable Long userId){
        return reserveService.findByUserId(userId);
    }

    @DeleteMapping("{userId}/{communityId}")
    public void deleteById(@PathVariable Long userId,@PathVariable Long communityId){
        reserveService.deleteById(userId, communityId);
    }

    @PutMapping("{communityId}")
    public void updateById(@PathVariable Long communityId, @RequestBody ReserveUpdateRequest request){
        reserveService.updateById(communityId,request);
    }
}
