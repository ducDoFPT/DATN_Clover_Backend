package com.datn.clover.DTO.Sellers;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ShopSellerBean {

    private String id;
    @NotBlank(message = "Name day is Null!")
    private String name;
    @NotBlank(message = "Province day is Null!")
    private String province;
    @NotBlank(message = "City day is Null!")
    private String city;
    @NotBlank(message = "District day is Null!")
    private String district;
    @NotBlank(message = "Wards day is Null!")
    private String wards;
    @NotBlank(message = "Street name number day is Null!")
    private String streetnameNumber;
    @NotBlank(message = "Nation day is Null!")
    private String nation;

}
