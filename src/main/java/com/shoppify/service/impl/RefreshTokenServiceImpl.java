package com.shoppify.service.impl;

import com.shoppify.service.RefreshTokenService;
import com.shoppify.entity.RefreshToken;
import com.shoppify.repository.RefreshTokenRepository;
import com.shoppify.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository tokenRepository;

    @Override
    public RefreshToken createNewToken(String username) {
        RefreshToken refreshToken =  RefreshToken.builder()
                .user(userRepository.findByUsername(username))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .build();
        return tokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return Optional.ofNullable(tokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Token isn't found")));
    }
}
