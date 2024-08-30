package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "detail_bills")
public class DetailBill {
    @Id
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Size(max = 150)
    @Column(name = "prod_name", length = 150)
    private String prodName;

    @Column(name = "price")
    private Float price;

    @Column(name = "percent_discount")
    private Integer percentDiscount;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_money")
    private Float totalMoney;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_id")
    @JsonIgnoreProperties({"detailBills", "shop", "prodType"
                            , "promotion", "evaluates", "prodCarts"
                            , "prodImages", "suppliers", "propertiesValues"})
    private Product prod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    @JsonIgnoreProperties({"detailBills", "account", "voucher"
                            , "addressBill", "shipBills"})
    private Bill bill;

}