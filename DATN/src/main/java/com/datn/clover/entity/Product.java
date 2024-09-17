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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

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
    @JsonIgnoreProperties({"products"})
    private TypeProduct prodType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    @JsonIgnoreProperties({"products", "account", "evaluatesFeedbacks", "staff"})
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    @JsonIgnoreProperties({"products", "account"})
    private Promotion promotion;

    @OneToMany(mappedBy = "prod")
    @JsonIgnoreProperties({"prod", "bill"})
    private Set<DetailBill> detailBills = new LinkedHashSet<>();

    @OneToMany(mappedBy = "prod")
    @JsonIgnoreProperties({"prod", "acc", "evaluatesFeedbacks"})
    private Set<Evaluate> evaluates = new LinkedHashSet<>();

    @OneToMany(mappedBy = "prod")
    @JsonIgnoreProperties({"prod", "cart"})
    private Set<ProdCart> prodCarts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "prod")
    @JsonIgnoreProperties({"prod"})
    private Set<ProdImage> prodImages = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "products")
    @JsonIgnoreProperties({"products"})
    private Set<Supplier> suppliers = new LinkedHashSet<>();

    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
        supplier.getProducts().add(this);
    }

    public void removeSupplier(Supplier supplier) {
        suppliers.remove(supplier);
        supplier.getProducts().remove(this);
    }

    @ManyToMany(mappedBy = "products")
    @JsonIgnoreProperties({"products", "properties"})
    private Set<PropertiesValue> propertiesValues = new LinkedHashSet<>();

    public void addPropertiesValue(PropertiesValue propertiesValue) {
        propertiesValues.add(propertiesValue);
        propertiesValue.getProducts().add(this);
    }

    public void removePropertiesValue(PropertiesValue propertiesValue) {
        propertiesValues.remove(propertiesValue);
        propertiesValue.getProducts().remove(this);
    }

}