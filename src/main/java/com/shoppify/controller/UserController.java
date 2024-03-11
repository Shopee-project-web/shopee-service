package com.shoppify.controller;

import com.shoppify.dto.UserDto;
import com.shoppify.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto){
        if(userService.createNewUser(userDto)){
            return ResponseEntity.status(HttpStatus.CREATED).body("User have been create!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already exist!");
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos = userService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(userDtos);
    }
}
