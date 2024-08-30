package com.datn.clover.responeObject;

import com.datn.clover.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BillResponse {

    private String id;


    private LocalDate buyDay;

    private Float totalPayment;

    private Float shipMoney;

    private Integer discount;

    private String paymentMethods;

    private String status;

    private Float discountVoucher;

    private String fullname;

    private String phone;
    private String email;

    private Account account;

    private VoucherResponse voucher;

    private AddressBillResponse addressBill;

    private Set<DetailBillResponse> detailBills = new LinkedHashSet<>();

    private ShipBillResponse shipBills;

}