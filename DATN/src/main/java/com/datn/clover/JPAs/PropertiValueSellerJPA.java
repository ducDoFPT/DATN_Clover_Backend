package com.datn.clover.JPAs;

import com.datn.clover.entity.PropertiesValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiValueSellerJPA extends JpaRepository<PropertiesValue, String> {
}
