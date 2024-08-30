package com.datn.clover.Bean.Sellers;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CustomerSupportBean {
    private String id;

    private LocalDate supportDay;

    private String fullname;

    private String phone;

    private String note;

    private String accountId;

    private TypeSupportBean typeSp;

}