package com.shoppify.dto.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileRequest {
    private String fullName;
    private String gender;
    private Date dateOfBirth;
    private String avatarUrl;
}
