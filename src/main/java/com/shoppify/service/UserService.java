package com.shoppify.service;

import com.shoppify.dto.UserDto;
import com.shoppify.entity.User;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findUserByUsername(String username);

    boolean createNewUser(UserDto userDto);

    boolean isUserExist(UserDto userDto);
    
}
