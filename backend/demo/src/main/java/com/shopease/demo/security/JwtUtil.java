package com.shopease.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
@Component
public class JwtUtil {
    private static final String SECRET = "shopease-super-secure-jwt-secret-key-256-bit-long";
    private static final long EXPIRATION = 1000 * 60 * 60 * 24;
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
    public String generateToken(String email){
        return Jwts.builder().subject(email).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + EXPIRATION)).signWith(key).compact();
    }

    public String extractEmail(String token){
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }

}
