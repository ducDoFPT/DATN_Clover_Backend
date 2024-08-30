package com.datn.clover.Bean.Sellers;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CartBean {
    private String id;

    private Integer quantity;

    private Float totalMoney;

    private Account account;


    private Set<Product> products = new LinkedHashSet<>();

}