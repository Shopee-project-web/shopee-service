package com.shoppify.converter;

import com.shoppify.dto.payload.request.UserProfileRequest;
import com.shoppify.dto.payload.response.UserProfileResponse;
import com.shoppify.entity.UserProfile;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserProfileConverter {
    private  ModelMapper modelMapper;
    public UserProfile toEntity(UserProfileRequest userProfileRequest){
        return modelMapper.map(userProfileRequest,UserProfile.class);
    }

    public UserProfileResponse toDto(UserProfile userProfile){
        return modelMapper.map(userProfile,UserProfileResponse.class);
    }
}
