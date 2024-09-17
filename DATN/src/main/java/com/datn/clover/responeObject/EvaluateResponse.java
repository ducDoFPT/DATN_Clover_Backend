package com.datn.clover.responeObject;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.EvaluatesFeedback;
import com.datn.clover.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@JsonIgnoreProperties({"username", "password"})
public class EvaluateResponse {
    private Integer id;

    private String content;

    private LocalDate evaluateDay;

    private Integer starCount;

    @JsonIgnoreProperties({"promotion", "prodType", "shop"
            , "detailBills", "evaluates", "prodCarts"
            , "prodImages", "suppliers", "propertiesValues"})
    private Product prod;

    @JsonIgnoreProperties({"role", "status", "notifications"
            , "addresses", "bills", "carts"
            , "comments", "evaluates", "interacts"
            , "likes", "posts", "promotions"
            , "respondComments", "shares", "shipBills"
            , "shops", "staff", "storages"
            , "vouchers", "accounts", "account"
            , "acc", "follows1", "follows2"
            , "friends1", "friends2"})
    private Account acc;

    @JsonIgnoreProperties({"evaluate", "shop"})
    private Set<EvaluatesFeedback> evaluatesFeedbacks = new LinkedHashSet<>();
}
