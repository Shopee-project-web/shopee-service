package com.shoppify.service;

import com.shoppify.dto.CommonResponse;
import com.shoppify.dto.payload.request.UserProfileRequest;
import com.shoppify.entity.User;
import com.shoppify.entity.UserProfile;

public interface UserProfileService {
    CommonResponse findUserProfileByUser(User user);

    void setDefaultProfile (User user);

    CommonResponse updateProfileById(UserProfileRequest request,User user);

    UserProfile findByUser (User user);

}
