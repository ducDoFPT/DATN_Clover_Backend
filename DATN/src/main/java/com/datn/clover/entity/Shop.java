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
@Table(name = "shops")
public class Shop {
    @Id
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Size(max = 150)
    @Column(name = "name", length = 150)
    private String name;

    @Size(max = 50)
    @Column(name = "province", length = 50)
    private String province;

    @Size(max = 50)
    @Column(name = "city", length = 50)
    private String city;

    @Size(max = 50)
    @Column(name = "district", length = 50)
    private String district;

    @Size(max = 50)
    @Column(name = "wards", length = 50)
    private String wards;

    @Size(max = 100)
    @Column(name = "streetname_number", length = 100)
    private String streetnameNumber;

    @Size(max = 50)
    @Column(name = "nation", length = 50)
    private String nation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties({"addresses", "account", "role"
            , "notifications", "staff", "shop"
            , "bills", "cart", "comments"
            , "evaluates", "follows1", "follows2"
            , "friends1", "friends2", "interacts"
            , "posts", "promotions", "respondComments"
            , "shares", "shipBills", "storages"
            , "vouchers", "likes"})
    private Account account;

    @OneToMany(mappedBy = "shop")
    @JsonIgnoreProperties({"detailBills", "shop", "prodType"
            , "promotion", "evaluates", "prodCarts"
            , "prodImages", "suppliers", "propertiesValues"})
    private List<Product> products;

    @OneToMany(mappedBy = "shop")
    @JsonIgnoreProperties({"account", "shop"})
    private List<Staff> staff;

}