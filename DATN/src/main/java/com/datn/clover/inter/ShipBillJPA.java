package com.datn.clover.inter;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.ShipBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipBillJPA extends JpaRepository<ShipBill, String> {
}
