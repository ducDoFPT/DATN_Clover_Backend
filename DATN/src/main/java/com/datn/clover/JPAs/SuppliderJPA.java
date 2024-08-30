package com.datn.clover.JPAs;

import com.datn.clover.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliderJPA extends JpaRepository<Supplier, String> {

}
