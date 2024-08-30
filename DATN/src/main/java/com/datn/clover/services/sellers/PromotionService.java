package com.datn.clover.services.sellers;

import com.datn.clover.Bean.Sellers.PromotionSellerBean;
import com.datn.clover.JPAs.PromotionJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Promotion;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {

    public Promotion savePromotion(PromotionSellerBean promotion, PromotionJPA promotionJPA, Account account) {
        Promotion promotionEntity = new Promotion();
        promotionEntity.setId(promotion.getId());
        promotionEntity.setName(promotion.getName());
        promotionEntity.setStartDay(promotion.getStartDay());
        promotionEntity.setEndDay(promotion.getEndDay());
        promotionEntity.setPercentDiscount(promotion.getPercentDiscount());
        promotionEntity.setAccount(account);
        return  promotionJPA.save(promotionEntity);
    }
    public  Promotion updatePromotion(PromotionSellerBean promotion, PromotionJPA promotionJPA, Account account) {
        Promotion promotionEntity = new Promotion();
        promotionEntity.setId(promotion.getId());
        promotionEntity.setName(promotion.getName());
        promotionEntity.setStartDay(promotion.getStartDay());
        promotionEntity.setEndDay(promotion.getEndDay());
        promotionEntity.setPercentDiscount(promotion.getPercentDiscount());
        promotionEntity.setAccount(account);
        return promotionJPA.save(promotionEntity);
    }
}
