package com.babiel.springsecurity.service;

import com.babiel.springsecurity.model.BlacklistEntity;

public interface BlacklistService {
    void addToken(BlacklistEntity blacklist);
    boolean isTokenBlacklisted(String token);
    void deleteAllEntries();
}
