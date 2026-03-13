package com.babiel.springsecurity.service;

import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

public interface JWTService {
    String generateToken(String username);
    Key getKey();
    boolean isTokenValid(String token);
    Claims getClaims(String token);
    String getSubject(String token);
    Date getExpiryDate(String token);
}
