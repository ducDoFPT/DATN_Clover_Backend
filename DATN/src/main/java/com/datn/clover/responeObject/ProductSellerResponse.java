package com.datn.clover.responeObject;

import com.datn.clover.entity.ProdImage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@JsonIgnoreProperties({"account","acc","accountId1", "accountId2","accounts"})
public class ProductSellerResponse {

    private String id;

    private String name;

    private Integer price;

    private Integer quantity;

    private String description;

    @JsonIgnoreProperties({"prod"})
    private List<ProdImage> prodImages;


}
