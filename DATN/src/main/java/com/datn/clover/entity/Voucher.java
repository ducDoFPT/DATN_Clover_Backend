package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "vouchers")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 150)
    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "voucher_value")
    private Integer voucherValue;

    @Column(name = "start_voucher")
    private Instant startVoucher;

    @Column(name = "end_voucher")
    private Instant endVoucher;

    @Column(name = "number_use")
    private Integer numberUse;

    @Column(name = "status")
    private Boolean status;

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
    @JoinColumn(name = "tvoucher_id")
    @JsonIgnoreProperties({"vouchers"})
    private TypeVoucher tvoucher;

    @OneToMany(mappedBy = "voucher")
    @JsonIgnoreProperties({"addressBill", "account", "voucher"
            , "status", "detailBills", "shipBills"})
    private Set<Bill> bills = new LinkedHashSet<>();

}