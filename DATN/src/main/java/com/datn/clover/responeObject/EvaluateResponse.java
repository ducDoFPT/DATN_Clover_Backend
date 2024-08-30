package com.datn.clover.responeObject;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EvaluateResponse {
    private String id;

    private String content;

    private LocalDate evaluateDay;

    private Integer starCount;

    private Product prod;

    private Account acc;

}