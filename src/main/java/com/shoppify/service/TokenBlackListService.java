package com.shoppify.service;

import jakarta.servlet.http.HttpServletRequest;

public interface TokenBlackListService {
    void addToBlackList(String token);

    boolean isBlackListed(String token);

    String extractTokenFromRequest(HttpServletRequest request);
}
