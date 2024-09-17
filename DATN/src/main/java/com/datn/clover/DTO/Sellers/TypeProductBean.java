package com.datn.clover.DTO.Sellers;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TypeProductBean {
    private String id;
    @NotBlank(message = "Name is Null!")
    private String name;

}