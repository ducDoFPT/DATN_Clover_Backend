package com.datn.clover.inter;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeProductJPA extends JpaRepository<TypeProduct, String> {
}
