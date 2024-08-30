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
public class ProdImageBean {
    private String id;

    private String name;

    private Product prod;

}