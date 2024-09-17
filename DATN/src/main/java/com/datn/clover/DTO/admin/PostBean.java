package com.datn.clover.DTO.admin;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostBean {

    @NotEmpty(message = "Vui lòng nhập mã bài viết!")
    @NotEmpty(message = "Vui lòng nhập mã bài viết!")
    private String id;

    @Size(max = 150, message = "Tiêu đề không được quá 150 ký tự!")
    @NotEmpty(message = "Vui lòng nhập tiêu đề bài viết!")
    @NotEmpty(message = "Vui lòng nhập tiêu đề bài viết!")
    private String title;

    @NotNull(message = "Ngày đăng không được để trống!")
    private LocalDateTime postDay;

    @Size(max = 225, message = "Nội dung không được quá 225 ký tự!")
    private String content;

    @Min(value = 0, message = "Số lược thích phải lớn hơn hoặc bằng 0!")
    private Integer numberLikes;

    @Size(max = 20, message = "Trạng thái không được quá 20 ký tự!")
    @NotEmpty(message = "Vui lòng nhập trạng thái bài viết!")
    @NotEmpty(message = "Vui lòng nhập trạng thái bài viết!")
    private String status;

    @Size(max = 20, message = "Trạng thái chia sẻ không được quá 20 ký tự!")
    @NotEmpty(message = "Vui lòng nhập trạng thái chia sẻ!")
    @NotEmpty(message = "Vui lòng nhập trạng thái chia sẻ!")
    private String stypeShare;

    @NotBlank(message = "Vui lòng nhập tài khoản!")
    @NotEmpty(message = "Vui lòng nhập tài khoản!")
    private String accountId;

}