//package com.shopee.controller.user;
//
//import com.shopee.controller.service.UserProfileService;
//import com.shopee.dto.CommonResponse;
//import com.shopee.dto.payload.request.UserProfileRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/user")
//@RequiredArgsConstructor
//public class UserController {
//    private final UserProfileService userProfileService;
//
//    @PostMapping("/updateProfile")
//    public ResponseEntity<CommonResponse> updateUserProfile(@RequestBody UserProfileRequest request){
//        return userProfileService.updateProfileById(request,)
//    }
//}
