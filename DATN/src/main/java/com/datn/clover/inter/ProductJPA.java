package com.datn.clover.inter;

import com.datn.clover.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJPA extends JpaRepository<Product, String> {
}
