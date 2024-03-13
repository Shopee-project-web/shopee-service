package com.shoppify.converter;

import com.shoppify.dto.payload.request.RegisterRequest;
import com.shoppify.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    private ModelMapper modelMapper;

    public UserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User convertDtoToUser(RegisterRequest requestDto){
        return modelMapper.map(requestDto,User.class);
    }
}
