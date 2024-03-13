package com.shoppify.controller;

import com.shoppify.dto.payload.request.AuthRequestDto;
import com.shoppify.dto.payload.response.JwtResponseDto;
import com.shoppify.service.impl.JwtService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public JwtResponseDto AuthenticateAndGetToken(@RequestBody AuthRequestDto authRequestDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequestDto.getUsername(),authRequestDto.getPassword()));
        if (authentication.isAuthenticated()){
            return JwtResponseDto.builder()
                    .accessToken(jwtService.generateToken(authRequestDto))
                    .build();
        }else {
            throw new UsernameNotFoundException("Username isn't found");
        }
    }

}
