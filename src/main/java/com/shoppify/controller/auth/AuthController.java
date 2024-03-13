package com.shoppify.controller.auth;

import com.shoppify.dto.payload.request.LoginRequest;
import com.shoppify.dto.payload.request.RegisterRequest;
import com.shoppify.dto.payload.response.AuthenticationResponse;
import com.shoppify.service.impl.AuthenticationService;
import com.shoppify.service.impl.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> getAccessToken(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.loginAuthenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody RegisterRequest request){
        if (authenticationService.register(request)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Create user successfully!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already exist, please choice another one");
    }
}
