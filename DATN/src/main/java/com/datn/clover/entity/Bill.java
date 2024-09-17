package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

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
    @JsonIgnoreProperties({"role", "status", "notifications"
            , "addresses", "bills", "carts"
            , "comments", "evaluates", "interacts"
            , "likes", "posts", "promotions"
            , "respondComments", "shares", "shipBills"
            , "shops", "staff", "storages"
            , "vouchers", "accounts", "account"
            , "acc", "follows1", "follows2"
            , "friends1", "friends2"})
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id")
    @JsonIgnoreProperties({"bills", "account", "tvoucher"})
    private Voucher voucher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    @JsonIgnoreProperties({"bills"})
    private BillStatus status;

    @OneToOne(mappedBy = "bills")
    @JsonIgnoreProperties({"bills"})
    private AddressBill addressBill;

    @OneToMany(mappedBy = "bill")
    @JsonIgnoreProperties({"bill", "prod"})
    private Set<DetailBill> detailBills = new LinkedHashSet<>();

    @OneToOne(mappedBy = "bill")
    @JsonIgnoreProperties({"bill", "acc"})
    private ShipBill shipBills;

}