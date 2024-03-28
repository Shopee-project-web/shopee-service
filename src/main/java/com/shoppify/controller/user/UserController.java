//package com.shoppify.controller.user;
//
//import com.shoppify.controller.service.UserProfileService;
//import com.shoppify.dto.CommonResponse;
//import com.shoppify.dto.payload.request.UserProfileRequest;
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
