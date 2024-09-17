package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "properties_value")
public class PropertiesValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 150)
    @Column(name = "name", length = 150)
    private String name;

    @Size(max = 225)
    @Column(name = "description", length = 225)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "properties_id")
    @JsonIgnoreProperties({"propertiesValues"})
    private Property properties;

    @ManyToMany
    @JoinTable(name = "prod_value",
            joinColumns = @JoinColumn(name = "value_id"),
            inverseJoinColumns = @JoinColumn(name = "prod_id"))
    @JsonIgnoreProperties({"promotion", "prodType", "shop"
            , "detailBills", "evaluates", "prodCarts"
            , "prodImages", "suppliers", "propertiesValues"})
    private Set<Product> products = new LinkedHashSet<>();

    public void addProduct(Product product) {
        products.add(product);
        product.getPropertiesValues().add(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.getPropertiesValues().remove(this);
    }

}