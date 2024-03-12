package com.shoppify.service.impl;

import com.shoppify.entity.RefreshToken;
import com.shoppify.entity.User;
import com.shoppify.repository.RefreshTokenRepository;
import com.shoppify.repository.UserRepository;
import com.shoppify.service.RefreshTokenService;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class RefreshTokenServiceImpl implements RefreshTokenService {
    private UserRepository userRepository;
    private RefreshTokenRepository tokenRepository;

    public RefreshTokenServiceImpl(UserRepository userRepository, RefreshTokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public RefreshToken createNewToken(String username) {
        RefreshToken refreshToken =  RefreshToken.builder()
                .user(userRepository.findUserByUsername(username))
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
