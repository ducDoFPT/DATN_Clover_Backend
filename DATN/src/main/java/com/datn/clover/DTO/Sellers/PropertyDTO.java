package com.datn.clover.DTO.Sellers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PropertyDTO {
    private Integer id;
    @NotBlank(message = "name is null!")
    private String name;
    @NotBlank(message = "Description is null!")
    private String description;

}
