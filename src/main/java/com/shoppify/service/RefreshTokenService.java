package com.shoppify.service;

import com.shoppify.entity.RefreshToken;
import com.shoppify.entity.User;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createNewToken(String username);

    Optional<RefreshToken> findByToken(String token);
}
