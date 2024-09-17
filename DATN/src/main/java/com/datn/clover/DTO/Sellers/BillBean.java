package com.datn.clover.DTO.Sellers;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BillBean {

    private String id;


    @NotNull(message = "Ship money is null!")
    @Min(value = 0, message = "Ship money > 0, please!")
    private Float shipMoney;

    @NotBlank(message = "Payment is null!")
    private String paymentMethods;

    @NotBlank(message = "Voucher is null!")
    private String voucher;



}