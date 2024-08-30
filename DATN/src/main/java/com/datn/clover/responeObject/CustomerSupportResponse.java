package com.datn.clover.responeObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CustomerSupportResponse {
    private String id;

    private LocalDate supportDay;

    private String fullname;

    private String phone;

    private String note;

    private String accountId;

    private TypeSupportResponse typeSp;

}