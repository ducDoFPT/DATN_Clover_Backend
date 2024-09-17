package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address_bill")
public class AddressBill {
    @Id
    @Column(name = "bill_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bill_id", nullable = false)
    @JsonIgnoreProperties({"addressBill", "account", "voucher"
            , "status", "detailBills", "shipBills"})
    private Bill bills;

    @Size(max = 50)
    @Column(name = "province_shop", length = 50)
    private String provinceShop;

    @Size(max = 50)
    @Column(name = "city_shop", length = 50)
    private String cityShop;

    @Size(max = 50)
    @Column(name = "district_shop", length = 50)
    private String districtShop;

    @Size(max = 50)
    @Column(name = "wards_shop", length = 50)
    private String wardsShop;

    @Size(max = 100)
    @Column(name = "streetnumber_shop", length = 100)
    private String streetnumberShop;

    @Size(max = 50)
    @Column(name = "nation_shop", length = 50)
    private String nationShop;

    @Size(max = 50)
    @Column(name = "province_receiver", length = 50)
    private String provinceReceiver;

    @Size(max = 50)
    @Column(name = "city_receiver", length = 50)
    private String cityReceiver;

    @Size(max = 50)
    @Column(name = "district_receiver", length = 50)
    private String districtReceiver;

    @Size(max = 50)
    @Column(name = "wards_receiver", length = 50)
    private String wardsReceiver;

    @Size(max = 100)
    @Column(name = "streetnumber_receiver", length = 100)
    private String streetnumberReceiver;

    @Size(max = 50)
    @Column(name = "nation_receiver", length = 50)
    private String nationReceiver;

}