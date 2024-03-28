package com.shoppify.dto.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserProfileResponse {
    private String fullName;
    private String gender;
    private Date dateOfBirth;
    private String avatarUrl;
    private LocalDateTime createOn;
    private LocalDateTime updateOn;
}
