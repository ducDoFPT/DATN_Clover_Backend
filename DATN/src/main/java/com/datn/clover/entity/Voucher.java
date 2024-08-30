package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "vouchers")
public class Voucher {
    @Id
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Size(max = 150)
    @Column(name = "name", length = 150)
    private String name;

    @Size(max = 150)
    @Column(name = "voucher_value", length = 150)
    private String voucherValue;

    @Column(name = "start_voucher")
    private LocalDateTime startVoucher;

    @Column(name = "end_voucher")
    private LocalDateTime endVoucher;

    @Column(name = "number_use")
    private Integer numberUse;

    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;

    @ManyToOne
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
    @JoinColumn(name = "tvoucher_id")
    @JsonIgnoreProperties("vouchers")
    private TypeVoucher tvoucher;

    @OneToMany(mappedBy = "voucher")
    @JsonIgnoreProperties({"detailBills", "account", "voucher"
            , "addressBill", "shipBills"})
    private List<Bill> bills;

}