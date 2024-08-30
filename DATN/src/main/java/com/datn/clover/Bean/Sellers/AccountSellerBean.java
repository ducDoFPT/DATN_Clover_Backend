package com.datn.clover.Bean.Sellers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.message.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class AccountSellerBean {
    private String id;
    @NotBlank(message = "Fullname is invalid!")
    private String fullname;
    @NotBlank(message = "Email is invalid!")
    private String email;
    @NotBlank(message = "Phone is invalid!")
    private String phone;
    @NotNull(message = "Gender is invalid!")
    private Boolean gender;
    private String addresses;

    private String username;
    private String password;
}
