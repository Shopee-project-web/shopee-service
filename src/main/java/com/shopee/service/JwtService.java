package com.shopee.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);

    Date extractExpiration(String token);

    Claims extractAllClaims(String token);

    <T> T extractClaim(String token, Function<Claims, T> resolver);

    String generateToken(String username);

    SecretKey getSignKey();

    boolean validateToken(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);

    Long getRemainingTime(String token);
}
