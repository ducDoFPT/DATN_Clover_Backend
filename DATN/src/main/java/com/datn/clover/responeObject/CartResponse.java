package com.datn.clover.responeObject;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CartResponse {
    private String id;

    private Integer quantity;

    private Float totalMoney;

    private Account account;


    private Set<Product> products = new LinkedHashSet<>();

}