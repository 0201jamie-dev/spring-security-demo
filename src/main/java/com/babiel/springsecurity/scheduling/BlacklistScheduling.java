package com.babiel.springsecurity.scheduling;

import com.babiel.springsecurity.service.BlacklistService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BlacklistScheduling {
    private final BlacklistService blacklistService;

    public BlacklistScheduling(BlacklistService blacklistService) {
        this.blacklistService = blacklistService;
    }
    @Scheduled(cron = "0 0 3 * * *", zone = "Europe/Paris")
    public void deleteAllBlacklistEntries() {
        blacklistService.deleteAllEntries();
    }
}
