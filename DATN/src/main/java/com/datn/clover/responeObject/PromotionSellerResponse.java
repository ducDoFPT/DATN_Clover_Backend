package com.datn.clover.responeObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
public class PromotionSellerResponse {

    private String id;
    private String name;
    private Integer percentDiscount;
    private Instant startDay;
    private Instant endDay;
    private String accountID;



}
