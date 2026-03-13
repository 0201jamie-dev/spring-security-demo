package com.babiel.springsecurity.service;

public interface CaptchaService {
    boolean verify(String token, String clientIp);
}
