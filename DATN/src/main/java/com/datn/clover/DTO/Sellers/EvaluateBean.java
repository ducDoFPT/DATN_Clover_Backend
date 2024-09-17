package com.datn.clover.DTO.Sellers;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EvaluateBean {
    private String id;
    @NotBlank(message = "Content is Null!")
    private String content;
    @NotNull(message = "Start count is Null!")
    @Min(value = 1, message = "Start count >1, Please!")
    private Integer starCount;
    @NotBlank(message = "Product is Null!")
    private String prodID;

}