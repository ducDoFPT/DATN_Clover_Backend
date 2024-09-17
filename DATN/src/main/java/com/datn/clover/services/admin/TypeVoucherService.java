package com.datn.clover.services.admin;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.TypeVoucher;
import com.datn.clover.inter.AccountJPA;
import com.datn.clover.inter.TypeVoucherJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeVoucherService {

    @Autowired
    private TypeVoucherJPA typeVoucherJPA;

    public List<TypeVoucher> getAllTypeVouchers() {
        return typeVoucherJPA.findAll();
    }
}
