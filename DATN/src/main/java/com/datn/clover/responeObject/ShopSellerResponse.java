package com.datn.clover.responeObject;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.EvaluatesFeedback;
import com.datn.clover.entity.Product;
import com.datn.clover.entity.Staff;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;
@Data
@JsonIgnoreProperties({"account","acc","accountId1", "accountId2","accounts"})
public class ShopSellerResponse {
    private int id;

    private String name;

    private String province;

    private String city;

    private String district;

    private String wards;

    private String streetnameNumber;

    private String nation;
    @JsonIgnoreProperties({"role", "status", "notifications"
            , "addresses", "bills", "carts"
            , "comments", "evaluates", "interacts"
            , "likes", "posts", "promotions"
            , "respondComments", "shares", "shipBills"
            , "shops", "staff", "storages"
            , "vouchers", "accounts", "account"
            , "acc", "follows1", "follows2"
            , "friends1", "friends2","username", "password"})
    private Account account;

    @JsonIgnoreProperties({"shop", "evaluate"})
    private Set<EvaluatesFeedback> evaluatesFeedbacks = new LinkedHashSet<>();

    @JsonIgnoreProperties({"promotion", "prodType", "shop"
            , "detailBills", "evaluates", "prodCarts"
            , "prodImages", "suppliers", "propertiesValues"})
    private Set<Product> products = new LinkedHashSet<>();

    @JsonIgnoreProperties({"shop", "account"})
    private Set<Staff> staff = new LinkedHashSet<>();
}
