package com.datn.clover.responeObject;

import com.datn.clover.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class BillResponse {
    private Integer id;

    private LocalDate buyDay;

    private Float totalPayment;

    private Float shipMoney;

    private Integer discount;

    private String paymentMethods;

    private Float discountVoucher;

    private String fullname;

    private String phone;

    private String email;

    @JsonIgnoreProperties({"role", "status", "notifications"
            , "addresses", "bills", "carts"
            , "comments", "evaluates", "interacts"
            , "likes", "posts", "promotions"
            , "respondComments", "shares", "shipBills"
            , "shops", "staff", "storages"
            , "vouchers", "accounts", "account"
            , "acc", "follows1", "follows2"
            , "friends1", "friends2", "username", "password"})
    private Account account;

    @JsonIgnoreProperties({"bills", "account", "tvoucher"})
    private Voucher voucher;
    @JsonIgnoreProperties({"bills"})
    private BillStatus status;

    @JsonIgnoreProperties({"bills"})
    private AddressBill addressBill;

    @JsonIgnoreProperties({"bill", "prod"})
    private Set<DetailBill> detailBills = new LinkedHashSet<>();

    @JsonIgnoreProperties({"bill", "acc"})
    private ShipBill shipBills;

}
