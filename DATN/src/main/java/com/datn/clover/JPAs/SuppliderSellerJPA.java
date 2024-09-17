package com.datn.clover.JPAs;

import com.datn.clover.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliderSellerJPA extends JpaRepository<Supplier, String> {

}
