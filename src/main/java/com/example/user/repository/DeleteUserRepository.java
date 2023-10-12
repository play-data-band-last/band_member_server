package com.example.user.repository;

import com.example.user.domain.entity.DeleteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeleteUserRepository extends JpaRepository<DeleteUser, Long> {

}
