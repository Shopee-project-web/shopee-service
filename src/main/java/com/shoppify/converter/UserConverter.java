package com.shoppify.converter;

import com.shoppify.dto.payload.request.RegisterRequest;
import com.shoppify.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverter {
    private final ModelMapper modelMapper;

    public User convertDtoToUser(RegisterRequest request){
        return modelMapper.map(request,User.class);
    }
}
