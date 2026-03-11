package com.babiel.springsecurity.service;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
}
