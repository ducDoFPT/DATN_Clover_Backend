package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "type_products")
public class TypeProduct {
    @Id
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Size(max = 150)
    @Column(name = "name", length = 150)
    private String name;

    @OneToMany(mappedBy = "prodType")
    @JsonIgnoreProperties({"detailBills", "shop", "prodType"
            , "promotion", "evaluates", "prodCarts"
            , "prodImages", "suppliers", "propertiesValues"})
    private List<Product> products;

}