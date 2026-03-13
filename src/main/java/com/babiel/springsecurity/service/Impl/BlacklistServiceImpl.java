package com.babiel.springsecurity.service.Impl;

import com.babiel.springsecurity.model.BlacklistEntity;
import com.babiel.springsecurity.repo.BlacklistRepository;
import com.babiel.springsecurity.service.BlacklistService;
import org.springframework.stereotype.Service;

@Service
public class BlacklistServiceImpl implements BlacklistService {
    private final BlacklistRepository blacklistRepository;

    public BlacklistServiceImpl(BlacklistRepository blacklistRepository) {
        this.blacklistRepository = blacklistRepository;
    }

    @Override
    public void addToken(BlacklistEntity blacklist) {
        blacklistRepository.save(blacklist);
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        return blacklistRepository.findByToken(token).isPresent();
    }

    @Override
    public void deleteAllEntries() {
        blacklistRepository.deleteAll();
    }
}
