package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "prod_cart")
public class ProdCart {
    @Id
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "intoMoney")
    private Float intoMoney;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_id")
    @JsonIgnoreProperties({"detailBills", "shop", "prodType"
            , "promotion", "evaluates", "prodCarts"
            , "prodImages", "suppliers", "propertiesValues"})
    private Product prod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    @JsonIgnoreProperties("prodCarts")
    private Cart cart;

}