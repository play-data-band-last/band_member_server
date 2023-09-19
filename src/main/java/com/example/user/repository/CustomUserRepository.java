package com.example.user.repository;

import com.example.user.domain.request.SignupRequest;

public interface CustomUserRepository {
    void updateUser(Long id, SignupRequest request);
}
