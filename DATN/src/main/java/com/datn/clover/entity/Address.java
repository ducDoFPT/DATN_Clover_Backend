package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

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

}