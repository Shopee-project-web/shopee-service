package com.shopee.controller.auth;

import com.shopee.service.impl.AuthenticationService;
import com.shopee.service.impl.JwtServiceImpl;
import com.shopee.dto.payload.request.LoginRequest;
import com.shopee.dto.payload.request.RegisterRequest;
import com.shopee.dto.payload.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtServiceImpl;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> getAccessToken(@RequestBody LoginRequest request, HttpServletResponse response) {
        return authenticationService.loginAuthenticate(request, response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@Valid @RequestBody RegisterRequest request, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            System.out.println(bindingResult.getModel());
            if (authenticationService.register(request)) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Đăng ký thành công!");
            }
        }
        System.out.println(bindingResult.getModel());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Đăng ký thất bại!");
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        authenticationService.logoutAuthenticate(request);
        return "logged out successfully!";
    }


}

