package com.datn.clover.DTO.Sellers;


import com.datn.clover.entity.PropertiesValue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProductSellerBean {

    private String id;
    @NotBlank(message = "Name is Null!")
    private String name;
    @NotNull(message = "Price is null!")
    @Min(value = 1,message = "Price is Null!")
    private Integer price;
    @NotNull(message = "Quantity is null!")
    @Min(value = 1, message = "Quantity is Null!")
    private Integer quantity;
    @NotBlank(message = "Description is Null!")
    private String description;
    @NotBlank(message = "Type Product is Null!")
    private String prodTypeID;
    @NotBlank(message = "Promotion is Null!")
    private String promotionID;
    @NotEmpty(message = "Property value is null!")
    private Set<String> propertiesValues = new LinkedHashSet<>();
    private String suppliers;
}
