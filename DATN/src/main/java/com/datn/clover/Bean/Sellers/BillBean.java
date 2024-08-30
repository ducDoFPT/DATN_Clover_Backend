package com.datn.clover.Bean.Sellers;

import com.datn.clover.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BillBean {

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

    private VoucherBean voucher;

    private AddressBillBean addressBill;

    private Set<DetailBillBean> detailBills = new LinkedHashSet<>();

    private ShipBillBean shipBills;

}