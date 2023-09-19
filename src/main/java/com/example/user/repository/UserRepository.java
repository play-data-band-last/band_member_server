package com.example.user.repository;

import com.example.user.domain.entity.User;
import com.example.user.domain.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, CustomUserRepository {
    //중복 이메일 검사
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);

    @Query("select new com.example.user.domain.response.UserResponse(u) " +
            "from User u " +
            "inner join fetch u.interest i " +
            "where u.id = :id")
    Optional<UserResponse> findInterestById (@Param("id") Long id );
}
