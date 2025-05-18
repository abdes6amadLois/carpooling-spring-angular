package org.example.covoiturage.security.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.example.covoiturage.security.entities.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static final String SECRET_KEY = "mySecretKey12345mySecretKey12345"; // Must be 32+ chars (256+ bits)
    private static final long EXPIRATION_TIME = 86400000; // 1 day in ms

    // Generate a secure signing key
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey()) // Just pass the key (algorithm inferred)
                .compact();
    }

    public String extractUsername(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(getSigningKey()) // Still works in 0.12.6 (but verifyWith is preferred)
                    .build()
                    .parseClaimsJws(token) // parseClaimsJws is still valid
                    .getPayload()
                    .getSubject();
        } catch (JwtException e) {
            throw new JwtException("Invalid JWT token: " + e.getMessage());
        }
    }
}