package com.datn.clover.DTO.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressBean {
    @Size(max = 10, message = "Mã địa chỉ không được quá 10 ký tự!")
    @NotEmpty(message = "Vui lòng nhập mã địa chỉ!")
    @NotBlank(message = "Vui lòng nhập mã địa chỉ!")
    private String id;

    @Size(max = 50, message = "Tỉnh không đươc quá 50 ký tự!")
    @NotEmpty(message = "Vui lòng nhập tỉnh thành!")
    @NotBlank(message = "Vui lòng nhập tỉnh thành!")
    private String province;

    @Size(max = 50, message = "Thành phố không được quá 50 ký tự!")
    @NotEmpty(message = "Vui lòng nhập thành phố!")
    @NotBlank(message = "Vui lòng nhập thành phố!")
    private String city;

    @Size(max = 50, message = "Quận/huyện không được quá 50 ký tự!")
    @NotEmpty(message = "Vui lòng nhập quận/huyện!")
    @NotBlank(message = "Vui lòng nhập quận/huyện!")
    private String district;

    @Size(max = 50, message = "Phường/xã không được quá 50 ký tự!")
    @NotEmpty(message = "Vui lòng nhập phường/xã!")
    @NotBlank(message = "Vui lòng nhập phường/xã!")
    private String wards;

    @Size(max = 100, message = "Số nhà, tên đường không được quá 100 ký tự!")
    @NotEmpty(message = "Vui lòng nhập số nhà, tên đường!")
    @NotBlank(message = "Vui lòng nhập số nhà, tên đường!")
    private String streetnameNumber;

    @Size(max = 50, message = "Quốc gia không được quá 50 ký tự!")
    @NotEmpty(message = "Vui lòng nhập quốc gia!")
    @NotBlank(message = "Vui lòng nhập quốc gia!")
    private String nation;

    @NotEmpty(message = "Vui lòng nhập tài khoản!")
    @NotBlank(message = "Vui lòng nhập tài khoản!")
    private String accountId;

}