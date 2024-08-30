package com.datn.clover.responeObject;

import com.datn.clover.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class VoucherResponse {
    private String id;

    private String name;

    private String voucherValue;

    private Instant startVoucher;

    private Instant endVoucher;

    private Integer numberUse;

    private String status;

    private Account account;

    private TypeVoucherResponse tvoucher;

    private Set<BillResponse> bills = new LinkedHashSet<>();

}