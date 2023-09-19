package com.example.user.repository;

import com.example.user.domain.request.ReserveUpdateRequest;

public interface CustomReserveRepository {
    void updateReserve(ReserveUpdateRequest request,Long communityId);
}
