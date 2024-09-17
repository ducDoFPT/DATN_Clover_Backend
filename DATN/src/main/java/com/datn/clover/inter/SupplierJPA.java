package com.datn.clover.inter;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierJPA extends JpaRepository<Supplier, String> {
}
