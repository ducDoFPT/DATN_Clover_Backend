package com.datn.clover.DTO.admin;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountBean {
    private String id;

    @Size(max = 20)
    @NotEmpty(message = "Vui lòng nhập tên đăng nhập!")
    @NotBlank(message = "Vui lòng nhập tên đăng nhập!")
    @Size(min = 5, max = 20, message = "Tên đăng nhập tối tiểu phải có 5 - 20 ký tự!")
    private String username;

    @NotBlank(message = "Vui lòng nhập mật khẩu!")
    @NotEmpty(message = "Vui lòng nhập mật khẩu!")
    @Size(min = 8, max = 8, message = "Mật khẩu phải có 8 ký tự! Trong đó có ít nhất 1 chữ cái in hoa, 1 chữ cái in thường và 1 số!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,8}$", message = "Mật khẩu phải có 8 ký tự! Trong đó có ít nhất 1 chữ cái in hoa, 1 chữ cái in thường và 1 số!")
    private String password;

    @NotEmpty(message = "Vui lòng nhập họ tên!")
    @NotBlank(message = "Vui lòng nhập họ tên!")
    @Size(min = 3, max = 50, message = "Họ tên tối thiểu phải có 3 - 50 kí tự!")
    private String fullname;

    @NotNull(message = "Vui lòng chọn giới tính!")
    private Boolean gender;

    @Size(max = 10, message = "Số điện thoại phải đủ 10 số!")
    @NotEmpty(message = "Vui lòng nhập số điện thoại!")
    @Pattern(regexp = "(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b", message = "Sai định dạng số điện thoại!")
    @NotBlank(message = "Vui lòng nhập số điện thoại!")
    private String phone;

    @Size(max = 50, message = "Email không được quá 50 ký tự!")
    @Email(message = "Email không đúng định dạng! VD:abc12@gmail.com")
    @NotBlank(message = "Vui lòng nhập email!")
    @NotEmpty(message = "Vui lòng nhập email!")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9]+@[a-zA-Z]+\\.(com)$", message = "Email không đúng định dạng! VD:abc12@gmail.com")
    private String email;

    @Size(max = 100)
    private String avatar;

    @NotEmpty(message = "Vui lòng chọn vai trò!")
    @NotBlank(message = "Vui lòng chọn vai trò!")
    private String roleId;

//    private List<Notification> notifications = new ArrayList<>();
//
//    private Address addresses;
//
//    private Staff staff;
//
//    private Shop shop;
//
//    private List<Bill> bills ;
//
//    private Cart cart;
//
//    private List<Comment> comments;
//
//    private List<Evaluate> evaluates;
//
//    private List<Follow> follows1;
//
//    private List<Follow> follows2;
//
//    private List<Friend> friends1;
//
//    private List<Friend> friends2;
//
//    private List<Interact> interacts = new ArrayList<>();
//
//    private List<Like> likes;
//
//    private List<Post> posts;
//
//    private List<Promotion> promotions;
//
//    private List<RespondComment> respondComments;
//
//    private List<Share> shares;
//
//    private List<ShipBill> shipBills ;
//
//    private List<Storage> storages ;
//
//    private List<Voucher> vouchers;

}