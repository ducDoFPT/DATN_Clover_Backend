package com.datn.clover.inter;

import com.datn.clover.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionJPA extends JpaRepository<Promotion, String> {
}
