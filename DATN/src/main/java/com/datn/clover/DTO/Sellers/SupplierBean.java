package com.datn.clover.DTO.Sellers;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SupplierBean {
    private String id;
    @NotBlank(message = "Name is Null!")
    private String name;
    @NotBlank(message = "Address is Null!")
    private String address;
    @NotBlank(message = "Phone is Null!")
    private String phone;


}