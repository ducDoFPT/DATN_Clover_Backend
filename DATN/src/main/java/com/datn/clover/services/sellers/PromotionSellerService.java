package com.datn.clover.services.sellers;

import com.datn.clover.DTO.Sellers.PromotionSellerBean;
import com.datn.clover.JPAs.PromotionSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Promotion;
import com.datn.clover.responeObject.PromotionSellerResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PromotionSellerService {

    public PromotionSellerResponse setResponse(Promotion promotion){
        PromotionSellerResponse response = new PromotionSellerResponse();
        response.setName(promotion.getName());
        response.setStartDay(promotion.getStartDay());
        response.setEndDay(promotion.getEndDay());
        response.setPercentDiscount(promotion.getPercentDiscount());
        return response;
    }

    public PromotionSellerResponse savePromotion(PromotionSellerBean promotion, PromotionSellerJPA promotionJPA, Account account) {
        Promotion promotionEntity = new Promotion();
        promotionEntity.setName(promotion.getName());
        promotionEntity.setStartDay(promotion.getStartDay());
        promotionEntity.setEndDay(promotion.getEndDay());
        promotionEntity.setPercentDiscount(promotion.getPercentDiscount());
        promotionEntity.setAccount(account);
        Promotion promotionRS = promotionJPA.save(promotionEntity);
        return  setResponse(promotionRS);
    }
    public  PromotionSellerResponse updatePromotion(PromotionSellerBean promotion, PromotionSellerJPA promotionJPA, Account account) {
        Optional<Promotion> promotionEntity = promotionJPA.findById(promotion.getId());
        if (promotionEntity.isPresent()) {
            promotionEntity.get().setName(promotion.getName());
            promotionEntity.get().setStartDay(promotion.getStartDay());
            promotionEntity.get().setEndDay(promotion.getEndDay());
            promotionEntity.get().setPercentDiscount(promotion.getPercentDiscount());
            promotionEntity.get().setAccount(account);
            Promotion promotionRS =  promotionJPA.save(promotionEntity.get());
            return setResponse(promotionRS);
        }

        return null;
    }
}
