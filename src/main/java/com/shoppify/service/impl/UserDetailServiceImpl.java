package com.shoppify.service.impl;

import com.shoppify.entity.Role;
import com.shoppify.entity.User;
import com.shoppify.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Username isn't found!");
        }
        List<Role> roles = new ArrayList<>();
        List<GrantedAuthority> auths = new ArrayList<>();
        for (Role role : user.getRoleList()){
            auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                auths
        );
        return userDetails;
    }
}
