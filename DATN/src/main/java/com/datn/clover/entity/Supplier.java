package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Size(max = 150)
    @Column(name = "name", length = 150)
    private String name;

    @Size(max = 150)
    @Column(name = "address", length = 150)
    private String address;

    @Size(max = 10)
    @Column(name = "phone", length = 10)
    private String phone;

    @ManyToMany
    @JoinTable(name = "prod_sup",
            joinColumns = @JoinColumn(name = "sup_id"),
            inverseJoinColumns = @JoinColumn(name = "prod_id"))
    @JsonIgnoreProperties({"detailBills", "shop", "prodType"
            , "promotion", "evaluates", "prodCarts"
            , "prodImages", "suppliers", "propertiesValues"})
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        product.getSuppliers().add(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.getSuppliers().remove(this);
    }

}