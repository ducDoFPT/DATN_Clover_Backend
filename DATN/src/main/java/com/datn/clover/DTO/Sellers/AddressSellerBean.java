package com.datn.clover.DTO.Sellers;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class
AddressSellerBean {

    private String id;
    @NotBlank(message = "Address is invalid!")
    private String province;
    @NotBlank(message = "City is invalid!")
    private String city;
    @NotBlank(message = "District is invalid!")
    private String district;
    @NotBlank(message = "Wards is invalid!")
    private String wards;
    @NotBlank(message = "StreetnameNumber is invalid!")
    private String streetnameNumber;
    @NotBlank(message = "City is invalid!")
    private String nation;
}