package com.datn.clover.services.admin;

import com.datn.clover.entity.Voucher;
import com.datn.clover.inter.VoucherJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherService {

    @Autowired
    private VoucherJPA voucherJPA;

    public List<Voucher> getAllVouchers() {
        return voucherJPA.findAll();
    }
}
