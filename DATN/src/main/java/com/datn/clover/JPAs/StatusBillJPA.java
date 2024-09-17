package com.datn.clover.JPAs;

import com.datn.clover.entity.BillStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusBillJPA extends JpaRepository<BillStatus, String> {

}
