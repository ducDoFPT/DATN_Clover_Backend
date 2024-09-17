package com.datn.clover.JPAs;

import com.datn.clover.entity.DetailBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailBillUserJPA extends JpaRepository<DetailBill, String> {

}
