package com.shoppify.dto.payload.request;


import com.shoppify.entity.PasswordAndConfirmPassword;
import com.shoppify.validator.PasswordValidator;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    @Pattern(regexp = "^[a-zA-Z0-9]{6,}$",message = "Tên người dùng phải có ít nhât 6 ký tự và không được có ký tự đặc biệt.")
    private String username;

    @Pattern(regexp = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$",
            message = "Email không hợp lệ.")
    private String email;

    @Pattern(regexp = "^(0|\\+84)\\d{9,11}$",message = "Số điện thoại không hợp lệ")
    private String phoneNumber;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Mật khẩu phải có ít nhất 8 ký tự bao gồm 1 chữ thường, 1 chữ in hoa, 1 số và 1 ký tự đặc biệt")
    private String password;

    @PasswordValidator
    private PasswordAndConfirmPassword passwordAndConfirmPassword;

}
