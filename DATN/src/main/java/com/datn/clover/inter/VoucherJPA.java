package com.datn.clover.inter;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherJPA extends JpaRepository<Voucher, String> {
}
