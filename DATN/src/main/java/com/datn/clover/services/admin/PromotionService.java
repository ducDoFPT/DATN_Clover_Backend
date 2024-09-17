package com.datn.clover.services.admin;

import com.datn.clover.entity.Promotion;
import com.datn.clover.inter.PromotionJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {

    @Autowired
    private PromotionJPA promotionJPA;
    public List<Promotion> getAllPromotions() {
        return promotionJPA.findAll();
    }
}
