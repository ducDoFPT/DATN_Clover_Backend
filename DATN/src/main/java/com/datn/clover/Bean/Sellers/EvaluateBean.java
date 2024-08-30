package com.datn.clover.Bean.Sellers;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EvaluateBean {
    private String id;

    private String content;

    private LocalDate evaluateDay;

    private Integer starCount;

    private String prodID;


}