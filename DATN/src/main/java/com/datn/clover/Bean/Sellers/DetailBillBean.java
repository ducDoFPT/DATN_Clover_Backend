package com.datn.clover.Bean.Sellers;

import com.datn.clover.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetailBillBean {
    private String id;

    private String prodName;

    private Float price;
    private Integer percentDiscount;
    private Integer quantity;
    private Float totalMoney;
    private Product prod;

    private BillBean bill;

}