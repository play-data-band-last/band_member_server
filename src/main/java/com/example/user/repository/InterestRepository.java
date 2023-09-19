package com.example.user.repository;

import com.example.user.domain.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest,Long> {
    @Query("SELECT i FROM Interest i WHERE i.user.id = :userId")
    List<Interest> findInterestsByUserId(@Param("userId") Long userId);
}
