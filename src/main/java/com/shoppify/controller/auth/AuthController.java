package com.shoppify.controller.auth;

import com.shoppify.dto.payload.request.LoginRequest;
import com.shoppify.dto.payload.request.RegisterRequest;
import com.shoppify.dto.payload.response.AuthenticationResponse;
import com.shoppify.service.impl.AuthenticationService;
import com.shoppify.service.impl.JwtServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtServiceImpl;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> getAccessToken(@RequestBody LoginRequest request, HttpServletResponse response){
        return authenticationService.loginAuthenticate(request,response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody RegisterRequest request){
        if (authenticationService.register(request)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Create user successfully!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already exist, please choice another one");
    }

    @PostMapping("/logout")
    public String logout(){
        SecurityContextHolder.clearContext();
        return "logged out successfully!";
    }
}
