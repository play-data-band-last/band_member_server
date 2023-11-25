package com.example.user.controller;

import com.example.user.domain.entity.Interest;
import com.example.user.domain.request.InterestRequest;
import com.example.user.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bandMember/{userId}/interest")
@CrossOrigin("*")
public class InterestController {
    private final InterestService interestService;

    @PostMapping
    public void saveInterests(@PathVariable Long userId,@RequestBody InterestRequest request){
        interestService.save(userId,request);
    }

}
