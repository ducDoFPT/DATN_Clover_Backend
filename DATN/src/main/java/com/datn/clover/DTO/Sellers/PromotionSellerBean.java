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

@Setter
@Getter
@NoArgsConstructor
public class PromotionSellerBean {
    private String id;
    @NotBlank(message = "Name is Null!")
    private String name;
    @NotNull(message = "Percent discount is null!")
    @Min(value = 1, message = "Percent discount >=1!")
    private Integer percentDiscount;
    @NotNull(message = "Start day is Null!")
    private Instant startDay;
    @NotNull(message = "End day is Null!")
    private Instant endDay;
}
