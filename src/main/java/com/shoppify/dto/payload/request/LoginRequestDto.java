package com.shoppify.dto.payload.request;

import com.shoppify.service.UserService;
import com.shoppify.service.impl.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginRequestDto {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtService jwtService;

    public LoginRequestDto(PasswordEncoder passwordEncoder, UserService userService, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtService = jwtService;
    }


}
