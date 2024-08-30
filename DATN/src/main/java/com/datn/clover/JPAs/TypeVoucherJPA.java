package com.datn.clover.JPAs;

import com.datn.clover.entity.TypeVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeVoucherJPA extends JpaRepository<TypeVoucher, String> {
}
