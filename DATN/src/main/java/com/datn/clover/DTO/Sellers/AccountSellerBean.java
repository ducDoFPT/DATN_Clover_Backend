package com.datn.clover.DTO.Sellers;

import jakarta.validation.constraints.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountSellerBean {

    private String id;

    @NotBlank(message = "Vui lòng nhập họ tên!")
    @Size(min = 3, max = 50, message = "Họ tên tối thiểu phải có 3 - 50 kí tự!")
    private String fullname;

    @NotNull(message = "Vui lòng chọn giới tính!")
    private Boolean gender;

    @Size(max = 10, message = "Số điện thoại phải đủ 10 số!")
    @Pattern(regexp = "(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b", message = "Sai định dạng số điện thoại!")
    @NotBlank(message = "Vui lòng nhập số điện thoại!")
    private String phone;

    @Size(max = 50, message = "Email không được quá 50 ký tự!")
    @Email(message = "Email không đúng định dạng! VD:abc12@gmail.com")
    @NotBlank(message = "Vui lòng nhập email!")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9]+@[a-zA-Z]+\\.(com)$", message = "Email không đúng định dạng! VD:abc12@gmail.com")
    private String email;


}
