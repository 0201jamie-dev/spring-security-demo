package com.babiel.springsecurity.repo;

import com.babiel.springsecurity.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameOrEmailAddress(String username, String emailAddress);
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmailAddress(String emailAddress);
}
