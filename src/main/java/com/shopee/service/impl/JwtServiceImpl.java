package com.shopee.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.shopee.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final Cache<String,String> tokenCache;
    private final String SECRET_KEY = "c94ffe2e6081a8620dd3287e1d6e794fbe05ad22fd76e2bb60214d4b5a700921";

    @Override
    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }
    @Override
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    @Override
    public Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    @Override
    public <T> T extractClaim(String token, Function<Claims, T> resolver){
        final Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }
    @Override
    public String generateToken(String username){
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+20000*60*1))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    @Override
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    @Override
    public Long getRemainingTime(String token) {
        Date expiryTime = extractExpiration(token);
        Date now = new Date();
        long remainingTimeMilis = expiryTime.getTime() - now.getTime();
        return remainingTimeMilis;
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())
                && !isTokenExpired(token)
                && !isTokenInBlackList(token));
    }

    protected void putTokenWithExpiry(String token, long expiryAfterWriteSeconds) {
        tokenCache.put(extractUsername(token),token);
    }

    protected boolean isTokenInBlackList(String token) {
        return tokenCache.getIfPresent(extractUsername(token)) != null;
    }
}