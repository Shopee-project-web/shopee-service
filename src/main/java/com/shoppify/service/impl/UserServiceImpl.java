package com.shoppify.service.impl;

import com.shoppify.service.UserService;
import com.shoppify.entity.User;
import com.shoppify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public boolean existUserByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public boolean existUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public boolean exitsUserByPhoneNumber(String phoneNumber) {
        return userRepository.existsUserByPhoneNumber(phoneNumber);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
