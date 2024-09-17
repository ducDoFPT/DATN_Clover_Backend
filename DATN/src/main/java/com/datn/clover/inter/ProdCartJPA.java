package com.datn.clover.inter;

import com.datn.clover.entity.ProdCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdCartJPA extends JpaRepository<ProdCart, String> {
}
