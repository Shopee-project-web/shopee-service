package com.shoppify.service.impl;

import com.shoppify.service.UserProfileService;
import com.shoppify.converter.UserConverter;
import com.shoppify.dto.payload.request.LoginRequest;
import com.shoppify.dto.payload.request.RegisterRequest;
import com.shoppify.dto.payload.response.AuthenticationResponse;
import com.shoppify.entity.Role;
import com.shoppify.entity.User;
import com.shoppify.repository.RoleRepository;
import com.shoppify.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtServiceImpl;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final UserConverter userConverter;
    private final UserProfileService userProfileService;

    public boolean register(RegisterRequest request){
        if (!userRepository.existsUserByUsername(request.getUsername())){
            Role role = roleRepository.findByName("ROLE_CUSTOMER");
            if (role != null) {

                User user = userConverter.convertDtoToUser(request);
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                List<Role> roles = new ArrayList<>();
                roles.add(role);
                user.setRoleList(roles);
                userRepository.save(user);
                userProfileService.setDefaultProfile(user);
                return true;
            }
        }
        return false;
    }

    public ResponseEntity<AuthenticationResponse> loginAuthenticate(LoginRequest loginRequest,HttpServletResponse response){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );
        if (authentication.isAuthenticated()){
            String accessToken = jwtServiceImpl.generateToken(loginRequest.getUsername());
            ResponseCookie cookie = ResponseCookie.from("accessToken",accessToken)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(18000)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return ResponseEntity.ok()
                    .body(AuthenticationResponse.builder()
                            .accessToken(accessToken)
                            .build());
        }else {
            throw new UsernameNotFoundException("Username isn't found");
        }
    }

    public ResponseEntity<String> logoutAuthenticate(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = null;
        if (authHeader!= null && authHeader.startsWith("Bearer")){
            token = authHeader.substring(7);
            Long expiryAfterWriteSeconds = jwtServiceImpl.getRemainingTime(token);
            jwtServiceImpl.putTokenWithExpiry(token,expiryAfterWriteSeconds);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Logged out successfully");
    }
}
