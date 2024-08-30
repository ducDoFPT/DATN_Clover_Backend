package com.datn.clover.responeObject;

import com.datn.clover.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PropertiesValueResponse {

    private String id;


    private String name;

    private String description;
    private PropertyResponse properties;

    private Set<Product> products = new LinkedHashSet<>();

}