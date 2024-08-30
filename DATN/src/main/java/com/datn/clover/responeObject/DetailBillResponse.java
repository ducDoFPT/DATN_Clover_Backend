package com.datn.clover.responeObject;

import com.datn.clover.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetailBillResponse {
    private String id;

    private String prodName;

    private Float price;
    private Integer percentDiscount;
    private Integer quantity;
    private Float totalMoney;
    private Product prod;

    private BillResponse bill;

}