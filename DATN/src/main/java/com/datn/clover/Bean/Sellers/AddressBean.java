package com.datn.clover.Bean.Sellers;

import com.datn.clover.entity.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class
AddressBean {

    private String id;
    @NotBlank(message = "Address is invalid!")
    private String province;
    @NotBlank(message = "City is invalid!")
    private String city;

    private String district;

    private String wards;

    private String streetnameNumber;

    private String nation;

    private Account account;

}