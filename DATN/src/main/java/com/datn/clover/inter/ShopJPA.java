package com.datn.clover.inter;

import com.datn.clover.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopJPA extends JpaRepository<Shop, String> {
}
