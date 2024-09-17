package com.datn.clover.DTO.Sellers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressBillBean {
    private String billId;

    private BillBean bill;

    private String provinceShop;

    private String cityShop;

    private String districtShop;

    private String wardsShop;

    private String streetnumberShop;

    private String nationShop;

    private String provinceReceiver;

    private String cityReceiver;

    private String districtReceiver;

    private String wardsReceiver;

    private String streetnumberReceiver;

    private String nationReceiver;

}