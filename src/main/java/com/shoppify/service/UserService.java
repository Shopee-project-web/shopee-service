package com.shoppify.service;

import com.shoppify.entity.User;

public interface UserService {
    boolean existUserByUsername(String username);

    boolean existUserByEmail (String email);

    boolean exitsUserByPhoneNumber(String phoneNumber);

    User findUserByUsername(String username);
}
