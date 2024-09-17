package com.datn.clover.JPAs;

import com.datn.clover.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertySellerJPA extends JpaRepository<Property, String> {
}
