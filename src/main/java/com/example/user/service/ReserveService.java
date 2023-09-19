package com.example.user.service;

import com.example.user.domain.entity.Reserve;
import com.example.user.domain.request.ReserveRequest;
import com.example.user.domain.request.ReserveUpdateRequest;
import com.example.user.domain.response.ReserveResponse;
import com.example.user.repository.ReserveRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReserveService {
    private final ReserveRepository reserveRepository;
    @Transactional
    public void save(Long userId, ReserveRequest request){
        reserveRepository.save(request.toEntity(userId));
    }

    @Transactional
    public List<ReserveResponse> findByUserId(Long userId){
        List<Reserve> allByUserId = reserveRepository.findAllByUserId(userId);
        return allByUserId
                .stream()
                .map(ReserveResponse::new)
                .toList();
    }

    @Transactional
    public void deleteById(Long userId, Long communityId){
        reserveRepository.deleteByUserIdAndCommunityId(userId,communityId);
    }

    @Transactional
    public void updateById(Long communityId, ReserveUpdateRequest reserveUpdateRequest){
        reserveRepository.updateReserve(reserveUpdateRequest,communityId);

    }

}
