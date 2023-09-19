package com.example.user.repository;

import com.example.user.domain.entity.QUser;
import com.example.user.domain.request.SignupRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository{
    private final JPAQueryFactory queryFactory;
    private QUser qUser = QUser.user;
    @Override
    public void updateUser(Long id, SignupRequest request) {
        JPAUpdateClause update = queryFactory.update(qUser);

        if(request.getEmail() !=null){
            update.set(qUser.email, request.getEmail());
        }
        if(request.getName() != null){
            update.set(qUser.name, request.getName());
        }
        if(request.getMbti() != null){
            update.set(qUser.mbti, request.getMbti());
        }
        if(request.getImgPath() != null){
            update.set(qUser.imgPath, request.getImgPath());
        }

        BooleanExpression eq = qUser.id.eq(id);
        update.where(eq);
        update.execute();
    }
}
