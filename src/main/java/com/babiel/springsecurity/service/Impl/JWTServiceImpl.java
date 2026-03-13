package com.babiel.springsecurity.service.Impl;

import com.babiel.springsecurity.service.JWTService;
import com.babiel.springsecurity.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JWTServiceImpl implements JWTService {
    @Value("${jwt.secret}")
    private String secretKey;
    private final UserService userService;

    public JWTServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .and()
                .signWith(getKey())
                .compact();
    }

    public SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token) {
        Claims claims = getClaims(token);

        if (claims == null) {return false;}

        String subject = getSubject(token);
        boolean isTokenExpired = claims.getExpiration().before(new Date());

        if (isTokenExpired) return false;
        if (subject == null) return false;
        return userService.existsUserByEmailAddress(subject);
    }

    public Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public String getSubject(String token) {
        Claims claims = getClaims(token);
        if (claims == null) {return null;}
        return claims.getSubject();
    }

    public Date getExpiryDate(String token) {
        Claims claims = getClaims(token);

        return claims.getExpiration();
    }
}
