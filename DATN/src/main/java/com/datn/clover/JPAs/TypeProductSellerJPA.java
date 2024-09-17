package com.datn.clover.JPAs;

import com.datn.clover.entity.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeProductSellerJPA extends JpaRepository<TypeProduct, String> {
}
