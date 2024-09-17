package com.datn.clover.DTO.Sellers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PropertivalueDTO {
    private Integer id;
    @NotBlank(message = "Name is blank!")
    private String name;
    @NotBlank(message = "Description is null!")
    private String description;
    @NotBlank(message = "Property is null")
    private String properties;

}
