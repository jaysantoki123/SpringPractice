package com.Placement.SpringPractice.UserRepository;

import com.Placement.SpringPractice.DTOs.UserResponse;
import com.Placement.SpringPractice.UserEntity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Spring Data JPA auto-generates all CRUD methods (save, findById, delete, etc.)
    Optional<UserEntity>  findByEmail(String email);
    Optional<UserEntity> findByName(String name);


}
