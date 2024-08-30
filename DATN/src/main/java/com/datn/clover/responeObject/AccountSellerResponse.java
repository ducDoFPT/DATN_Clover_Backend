package com.datn.clover.responeObject;

import com.datn.clover.entity.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountSellerResponse {
    private String fullname;
    private String email;
    private String phone;
    private Boolean gender;
    @JsonIgnoreProperties({"account"})
    private Address addresses;
    private String avatar;
}
