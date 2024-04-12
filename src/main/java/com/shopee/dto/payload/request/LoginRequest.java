package com.shopee.dto.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    @NotBlank(message = "Tên đăng nhập không hợp lệ.")
    private String username;
    @NotBlank(message = "Mật khẩu không hợp lệ.")
    private String password;
}


