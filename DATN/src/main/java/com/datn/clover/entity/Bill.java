package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Column(name = "buy_day")
    private LocalDate buyDay;

    @Column(name = "total_payment")
    private Float totalPayment;

    @Column(name = "ship_money")
    private Float shipMoney;

    @Column(name = "discount")
    private Integer discount;

    @Size(max = 50)
    @Column(name = "payment_methods", length = 50)
    private String paymentMethods;

    @Size(max = 20)
    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "discount_voucher")
    private Float discountVoucher;

    @Size(max = 50)
    @Column(name = "fullname", length = 50)
    private String fullname;

    @Size(max = 10)
    @Column(name = "phone", length = 10)
    private String phone;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id")
    @JsonIgnoreProperties({"bills", "account"})
    private Voucher voucher;

    @OneToOne(mappedBy = "bills")
    @JsonIgnoreProperties("bills")
    private AddressBill addressBill;

    @OneToMany(mappedBy = "bill")
    @JsonIgnoreProperties({"bill", "prod"})
    private List<DetailBill> detailBills;

    @OneToOne(mappedBy = "bill")
    @JsonIgnoreProperties({"bill", "bills", "acc"})
    private ShipBill shipBills;

}