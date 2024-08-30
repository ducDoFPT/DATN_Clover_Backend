package com.datn.clover.Bean.Sellers;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class ProductSellerBean {

    private String id;

    private String name;

    private Integer price;

    private Integer quantity;

    private String description;

    private String prodTypeID;

    private String shopID;

    private String promotionID;


    private String suppliers;

}
