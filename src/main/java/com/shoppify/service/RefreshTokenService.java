package com.shoppify.service;

import com.shoppify.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createNewToken(String username);

    Optional<RefreshToken> findByToken(String token);
}
