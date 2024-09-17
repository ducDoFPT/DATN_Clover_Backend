package com.datn.clover.responeObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties({"account","acc","accountId1", "accountId2","accounts"})

public class PromotionSellerResponse {
    private String id;

    private String name;

    private Integer percentDiscount;

    private Instant startDay;

    private Instant endDay;



}
