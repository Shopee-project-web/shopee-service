package com.shopee.service;

import com.shopee.dto.CommonResponse;
import com.shopee.dto.payload.request.UserProfileRequest;
import com.shopee.entity.User;
import com.shopee.entity.UserProfile;

public interface UserProfileService {
    CommonResponse findUserProfileByUser(User user);

    void setDefaultProfile (User user);

    CommonResponse updateProfileById(UserProfileRequest request,User user);

    UserProfile findByUser (User user);

}
