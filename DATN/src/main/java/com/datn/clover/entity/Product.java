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
@Table(name = "products")
public class Product {
    @Id
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Size(max = 150)
    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "quantity")
    private Integer quantity;

    @Size(max = 225)
    @Column(name = "description", length = 225)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_type")
    @JsonIgnoreProperties("products")
    private TypeProduct prodType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    @JsonIgnoreProperties({"products", "account", "staff"})
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    @JsonIgnoreProperties({"products", "account"})
    private Promotion promotion;

    @OneToMany(mappedBy = "prod")
    @JsonIgnoreProperties({"bill", "prod"})
    private List<DetailBill> detailBills;

    @OneToMany(mappedBy = "prod")
    @JsonIgnoreProperties({"prod","acc"})
    private List<Evaluate> evaluates;

    @OneToMany(mappedBy = "prod")
    @JsonIgnoreProperties({"prod","cart"})
    private List<ProdCart> prodCarts;

    @OneToMany(mappedBy = "prod")
    @JsonIgnoreProperties("prod")
    private List<ProdImage> prodImages;

    @ManyToMany(mappedBy = "products")
    @JsonIgnoreProperties("products")
    private List<Supplier> suppliers = new ArrayList<>();

    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
        supplier.getProducts().add(this);
    }

    public void removeSupplier(Supplier supplier) {
        suppliers.remove(supplier);
        supplier.getProducts().remove(this);
    }

    @ManyToMany(mappedBy = "products")
    @JsonIgnoreProperties({"products","properties"})
    private List<PropertiesValue> propertiesValues = new ArrayList<>();

    public void addPropertiesValue(PropertiesValue propertiesValue) {
        propertiesValues.add(propertiesValue);
        propertiesValue.getProducts().add(this);
    }

    public void removePropertiesValue(PropertiesValue propertiesValue) {
        propertiesValues.remove(propertiesValue);
        propertiesValue.getProducts().remove(this);
    }

}