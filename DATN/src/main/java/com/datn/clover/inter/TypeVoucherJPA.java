package com.datn.clover.inter;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.TypeVoucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeVoucherJPA extends JpaRepository<TypeVoucher, String> {
}
