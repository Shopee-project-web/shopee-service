package com.shoppify.service.impl;

import com.shoppify.dto.response.UserDto;
import com.shoppify.entity.Role;
import com.shoppify.entity.User;
import com.shoppify.repository.RoleRepository;
import com.shoppify.repository.UserRepository;
import com.shoppify.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private RoleRepository roleRepository;
    private final String CHARACTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final int MIN_LENGTH = 8;
    private final int MAX_LENGTH = 12;
    private final Random RANDOM = new Random();

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
            Optional<Role> customerRole = roleRepository.findByName("ROLE_CUSTOMER");
            if (customerRole.isPresent()){
                List<Role> roles = new ArrayList<>();
                roles.add(customerRole.get());
                User user = User.builder()
                        .username(autoGenerateUsername())
                        .phoneNumber(userDto.getPhoneNumber())
                        .password(BCrypt.hashpw(userDto.getPassword(),BCrypt.gensalt()))
                        .roleList(roles)
                        .build();

                userRepository.save(user);
            }else {
                return false;
            }
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

    private String autoGenerateUsername(){
        int length = RANDOM.nextInt(MAX_LENGTH - MIN_LENGTH +1 ) + MIN_LENGTH;
        StringBuilder randomUsername = new StringBuilder();
        for (int i = 0; i < length; i++){
            randomUsername.append(CHARACTER.charAt(RANDOM.nextInt(CHARACTER.length())));
        }
        return randomUsername.toString();
    }


}
