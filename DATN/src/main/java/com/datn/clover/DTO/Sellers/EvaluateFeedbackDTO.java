package com.datn.clover.DTO.Sellers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EvaluateFeedbackDTO {

    private Integer id;

    @NotBlank(message = "Content is null!")
    private String content;

    @NotBlank(message = "Evaluate is null!")
    private String evaluate;

}
