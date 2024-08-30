package com.datn.clover.JPAs;

import com.datn.clover.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionJPA extends JpaRepository<Promotion, String> {
    @Query("select p from Promotion p where  p.account.staff.shop.id = :id ")
    List<Promotion> findByAccount(@Param("id") String id);
}
