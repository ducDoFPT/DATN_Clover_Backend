package com.datn.clover.inter;

import com.datn.clover.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartJPA extends JpaRepository<Cart, String> {
}
