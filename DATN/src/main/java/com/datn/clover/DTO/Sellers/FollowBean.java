package com.datn.clover.DTO.Sellers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FollowBean {
    private Integer id;
    private Boolean status;
    @NotBlank(message = "Account 2 is null!")
    private String accountId2;
}
