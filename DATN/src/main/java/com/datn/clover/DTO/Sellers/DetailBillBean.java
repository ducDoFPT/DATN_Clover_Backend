package com.datn.clover.DTO.Sellers;

import com.datn.clover.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetailBillBean {
    private String id;
    @NotBlank(message = "Product name is null!")
    @Size(min = 1, max = 50, message = "Product name 1-50 letter!")
    private String prodName;
    @NotNull(message = "Price is null!")
    @Min(value = 1000, message = "Price >1000!")
    private Float price;
    @NotNull(message = "Quantity is null!")
    @Min(value = 1 , message = "Quantity >= 1")
    private Integer quantity;
}