package com.shoppify.service.impl;

import com.shoppify.dto.UserDto;
import com.shoppify.entity.Role;
import com.shoppify.entity.User;
import com.shoppify.repository.RoleRepository;
import com.shoppify.repository.UserRepository;
import com.shoppify.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        return mapToUserDto(user);
    }

    @Override
    public boolean createNewUser(UserDto userDto) {
        if(isUserExist(userDto)){
            return false;
        }else {
            User user = mapToUser(userDto);
            Optional<Role> customerRole = roleRepository.findByName("ROLE_CUSTOMER");
            if (customerRole.isPresent()){
                List<Role> roles = new ArrayList<>();
                roles.add(customerRole.get());
                user.setRoleList(roles);
            }else {
                return false;
            }
            userRepository.save(user);
        }
        return true;
    }

    @Override
    public boolean isUserExist(UserDto userDto) {
        return userRepository.existsUserByUsername(userDto.getUsername());
    }

    public User mapToUser(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }

    public UserDto mapToUserDto(User user){
        return modelMapper.map(user, UserDto.class);
    }


}
