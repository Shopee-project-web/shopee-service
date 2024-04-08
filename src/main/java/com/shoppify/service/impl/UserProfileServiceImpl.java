package com.shoppify.service.impl;

import com.shoppify.service.UserProfileService;
import com.shoppify.converter.UserProfileConverter;
import com.shoppify.dto.CommonResponse;
import com.shoppify.dto.payload.request.UserProfileRequest;
import com.shoppify.entity.User;
import com.shoppify.entity.UserProfile;
import com.shoppify.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserProfileConverter converter;
    @Override
    public CommonResponse findUserProfileByUser(User user) {
        CommonResponse commonResponse =new CommonResponse();
       Optional<UserProfile> userProfile = Optional.ofNullable(userProfileRepository.findUserProfileByUser(user));
       if (userProfile.isPresent()){
           commonResponse.setData(userProfile.get());
           commonResponse.setMessage("Lấy dữ liệu thành công!");
           commonResponse.setStatusCode(HttpStatus.OK);
       } else {
           commonResponse.setData(null);
           commonResponse.setMessage("Không tìm thấy dữ liệu!");
           commonResponse.setStatusCode(HttpStatus.NOT_FOUND);
       }
        return commonResponse;
    }

    @Override
    public void setDefaultProfile(User user) {
        UserProfile userProfile = UserProfile.builder()
                .user(user)
                .build();
        userProfileRepository.save(userProfile);
    }

    @Override
    public CommonResponse updateProfileById(UserProfileRequest request,User user) {
        UserProfile userProfile = findByUser(user);
        CommonResponse commonResponse = new CommonResponse();
            if (user != null) {
                userProfile.setFullName(request.getFullName());
                userProfile.setDateOfBirth(request.getDateOfBirth());
                userProfile.setGender(request.getGender());
                userProfile.setAvatarUrl(request.getAvatarUrl());

                commonResponse.setData(userProfile);
                commonResponse.setMessage("Cập nhật thông tin thành công");
                commonResponse.setStatusCode(HttpStatus.OK);
            } else {
                commonResponse.setData(null);
                commonResponse.setMessage("Cập nhật thông tin thất bại");
                commonResponse.setStatusCode(HttpStatus.NOT_FOUND);
            }

        return commonResponse;
    }

    @Override
    public UserProfile findByUser(User user) {
        return  userProfileRepository.findUserProfileByUser(user);

    }

}
