package com.datn.clover.responeObject;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class ProductSellerResponse {

    private String id;

    private String name;

    private Integer price;

    private Integer quantity;

    private String description;

    private String prodTypeID;

    private String shopID;

    private String promotionID;

    private final Set<MultipartFile> prodImages = new LinkedHashSet<>();

    private String suppliers;

}
