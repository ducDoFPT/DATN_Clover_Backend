package com.datn.clover.Bean.Sellers;

import com.datn.clover.entity.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ShipBillBean {
    private String id;

    private LocalDate shipDay;

    private String fullname;

    private String phone;

    private String email;

    private String status;

    private Boolean typeShip;

    private Account acc;

    private BillBean bill;

}