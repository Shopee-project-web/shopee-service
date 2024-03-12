package com.shoppify.service;

import com.shoppify.dto.response.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findUserByUsername(String username);

    boolean createNewUser(UserDto userDto);

    boolean isUserExist(UserDto userDto);
    
}
