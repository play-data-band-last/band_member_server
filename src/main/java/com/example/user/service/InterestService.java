package com.example.user.service;

import com.example.user.domain.entity.Interest;
import com.example.user.domain.entity.User;
import com.example.user.domain.request.InterestRequest;
import com.example.user.repository.InterestRepository;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class InterestService {
    private final InterestRepository interestRepository;

    public void save(Long userId, InterestRequest request){
        interestRepository.save(request.toEntity(userId));
    }


}
