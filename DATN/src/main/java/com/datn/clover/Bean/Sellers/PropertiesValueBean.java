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
public class PropertiesValueBean {

    private String id;


    private String name;

    private String description;
    private PropertyBean properties;

    private Set<Product> products = new LinkedHashSet<>();

}