package com.datn.clover.inter;

import com.datn.clover.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillJPA extends JpaRepository<Bill, String> {
}
