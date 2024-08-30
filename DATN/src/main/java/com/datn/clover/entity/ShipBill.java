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
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

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

    @Size(max = 20)
    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "type_ship")
    private Boolean typeShip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acc_id")
    @JsonIgnoreProperties({"addresses", "account", "role"
            , "notifications", "staff", "shop"
            , "bills", "cart", "comments"
            , "evaluates", "follows1", "follows2"
            , "friends1", "friends2", "interacts"
            , "posts", "promotions", "respondComments"
            , "shares", "shipBills", "storages"
            , "vouchers", "likes"})
    private Account acc;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    @JsonIgnoreProperties({"detailBills", "account", "voucher"
                         , "addressBill", "shipBills"})
    private Bill bill;

}