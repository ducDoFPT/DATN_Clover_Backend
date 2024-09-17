package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "ship_bills")
public class ShipBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ship_day")
    private LocalDate shipDay;

    @Size(max = 50)
    @Column(name = "fullname", length = 50)
    private String fullname;

    @Size(max = 10)
    @Column(name = "phone", length = 10)
    private String phone;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "type_ship")
    private Boolean typeShip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acc_id")
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    @JsonIgnoreProperties({"shipBills", "account", "voucher"
            , "status", "addressBill", "detailBills"})
    private Bill bill;

}