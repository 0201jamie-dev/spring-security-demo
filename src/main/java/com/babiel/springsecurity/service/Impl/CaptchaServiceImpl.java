package com.babiel.springsecurity.service.Impl;

import com.babiel.springsecurity.service.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.EventListener;
import java.util.Map;

@Service
@Slf4j
public class CaptchaServiceImpl implements CaptchaService {
    private final RestTemplate restTemplate;
    private final String verifyUrl = "https://www.google.com/recaptcha/api/siteverify";

    @Value("${google.recaptcha.secret}")
    private String secretKey;

    public CaptchaServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean verify(String token, String clientIp) {
        if (token == null || token.isBlank()) return false;

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("secret", secretKey);
        data.add("response", token);
        data.add("remoteip", clientIp);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(verifyUrl, request, Map.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            Object success = response.getBody().get("success");
            if (Boolean.TRUE.equals(success)) {
                Object captchaScore = response.getBody().get("score");
                if (captchaScore == null) return false;
                LOG.info("The recaptcha v3 score is: " + captchaScore);
                return (Double) captchaScore > 0.5;
            }
        }
        return false;
    }
}
