package com.datn.clover.Bean.Sellers;

import com.datn.clover.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TypeProductBean {
    private String id;

    private String name;
    private Set<Product> products = new LinkedHashSet<>();

}