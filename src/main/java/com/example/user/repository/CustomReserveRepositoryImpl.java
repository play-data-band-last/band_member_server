package com.example.user.repository;

import com.example.user.domain.entity.QReserve;
import com.example.user.domain.request.ReserveUpdateRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomReserveRepositoryImpl implements CustomReserveRepository{
   private final JPAQueryFactory queryFactory;
   private QReserve qReserve = QReserve.reserve;
    @Override
    public void updateReserve(ReserveUpdateRequest request, Long communityId) {
        JPAUpdateClause update = queryFactory.update(qReserve);

        if(request.getCommunityImgPath() != null){
            update.set(qReserve.communityImgPath, request.getCommunityImgPath());
        }
        if(request.getCommunityName() !=null){
            update.set(qReserve.communityName, request.getCommunityName());
        }

        BooleanExpression eq = qReserve.communityId.eq(communityId);
        update.where(eq);
        update.execute();

    }
}
