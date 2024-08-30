package com.datn.clover.responeObject;

import com.datn.clover.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ShipBillResponse {
    private String id;

    private LocalDate shipDay;

    private String fullname;

    private String phone;

    private String email;

    private String status;

    private Boolean typeShip;

    private Account acc;

    private BillResponse bill;

}