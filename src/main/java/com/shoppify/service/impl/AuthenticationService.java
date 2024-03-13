package com.shoppify.service.impl;

import com.shoppify.converter.UserConverter;
import com.shoppify.dto.payload.request.LoginRequest;
import com.shoppify.dto.payload.request.RegisterRequest;
import com.shoppify.dto.payload.response.AuthenticationResponse;
import com.shoppify.entity.Role;
import com.shoppify.entity.User;
import com.shoppify.repository.RoleRepository;
import com.shoppify.repository.UserRepository;
import lombok.AllArgsConstructor;
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
    private final JwtService jwtService;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final UserConverter userConverter;

    public boolean register(RegisterRequest request){
        if (!userRepository.existsUserByUsername(request.getUsername())){
            Role role = roleRepository.findByName("CUSTOMER");
            if (role != null) {
                User user = userConverter.convertDtoToUser(request);
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                List<Role> roles = new ArrayList<>();
                roles.add(role);
                user.setRoleList(roles);

                userRepository.save(user);
                return true;
            }
            return false;
        }
        return false;
    }

    public AuthenticationResponse loginAuthenticate(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );
        if (authentication.isAuthenticated()){
            return AuthenticationResponse.builder()
                    .accessToken(jwtService.generateToken(loginRequest))
                    .build();
        }else {
            throw new UsernameNotFoundException("Username isn't found");
        }
    }
}
