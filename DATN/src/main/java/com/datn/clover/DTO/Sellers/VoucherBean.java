package com.datn.clover.DTO.Sellers;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class VoucherBean {
    private String id;
    @NotBlank(message = "Name is Null!")
    private String name;
    @NotNull(message = "Voucher is null!")
    @Min(value = 1 , message = "Voucher value >0!")
    private int voucherValue;
    @NotNull(message = "Start date is Null!")
    private Instant startVoucher;
    @NotNull(message = "End date is Null!")
    private Instant endVoucher;
    @NotNull(message = "Number user is Null!")
    @Min(value = 1, message = "Number use >1")
    private Integer numberUse;
    @NotNull(message = "Status is Null!")
    private boolean status;
    @NotBlank(message = "Name is Null!")
    private String tvoucher;
}