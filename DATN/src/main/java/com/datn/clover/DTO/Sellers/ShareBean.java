package com.datn.clover.DTO.Sellers;

import com.datn.clover.entity.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ShareBean {
    private String id;
    @NotBlank(message = "Receiver is nul!")
    private String receiver;

    private String post;

}