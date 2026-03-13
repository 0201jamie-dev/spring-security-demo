package com.babiel.springsecurity.repo;

import com.babiel.springsecurity.model.BlacklistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlacklistRepository extends JpaRepository<BlacklistEntity, Long> {
    Optional<BlacklistEntity> findByToken(String token);
}
