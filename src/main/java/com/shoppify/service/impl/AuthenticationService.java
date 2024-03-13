package com.shoppify.service.impl;

import com.shoppify.converter.UserConverter;
import com.shoppify.dto.payload.request.LoginRequestDto;
import com.shoppify.dto.payload.request.RegisterRequestDto;
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
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;
    private final UserConverter userConverter;

    public boolean register(RegisterRequestDto requestDto){
        if (!userRepository.existsUserByUsername(requestDto.getUsername())){
            Role role = roleRepository.findByName("CUSTOMER");
            if (role != null) {
                User user = userConverter.convertDtoToUser(requestDto);
                user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
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

    public AuthenticationResponse loginAuthenticate(LoginRequestDto loginRequestDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()
            )
        );
        if (authentication.isAuthenticated()){
            return AuthenticationResponse.builder()
                    .accessToken(jwtService.generateToken(loginRequestDto))
                    .build();
        }else {
            throw new UsernameNotFoundException("Username isn't found");
        }
    }
}
