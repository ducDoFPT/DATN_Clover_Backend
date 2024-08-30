package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.Bean.Sellers.PromotionSellerBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.PromotionJPA;
import com.datn.clover.JPAs.ShopJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Promotion;
import com.datn.clover.entity.Shop;
import com.datn.clover.services.sellers.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seller/promotion")
public class PromotionController {
    @Autowired
    PromotionJPA promotionJPA;
    @Autowired
    private AccountSellerJPA accountJPA;
    @Autowired
    private ShopJPA shopJPA;
    @Autowired
    private PromotionService promotionService;

    @GetMapping("/getAllPromotionByShop/{username}")
    public List<Promotion> getAllPromtionByShop(@PathVariable("username") String username) {
        Optional<Account> account = accountJPA.getAccountByUsername(username);
        return account.map(value -> promotionJPA.findByAccount(value.getStaff().getShop().getId())).orElse(null);
    }

    @PostMapping("/createPromotion/{username}")
    public ResponseEntity<Promotion> createPromotion(@PathVariable("username") String username, @RequestBody PromotionSellerBean promotion) {
        try {
            Optional<Account> account = accountJPA.getAccountByUsername(username);
            return account.map(value -> ResponseEntity.ok(promotionService.savePromotion(promotion, promotionJPA, value))).orElseGet(() -> ResponseEntity.notFound().build());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/updatePromotion/{username}")
    public ResponseEntity<Promotion> updatePromotion(@PathVariable("username") String username, @RequestBody PromotionSellerBean promotion) {
        try {
            Optional<Account> account = accountJPA.getAccountByUsername(username);
            if (account.isPresent()) {
                return account.map(value -> ResponseEntity.ok(promotionService.updatePromotion(promotion, promotionJPA, value))).orElseGet(() -> ResponseEntity.notFound().build());
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{token}")
    public boolean deletePromotion(@PathVariable("token") String username,@RequestParam("id") String id) {
        try {
            Optional<Account> account = accountJPA.getAccountByUsername(username);
            if (account.isEmpty()) {
                return false;
            }
            Optional<Promotion> promotion = promotionJPA.findById(id);
            if (promotion.isEmpty()) {
                return false;
            }
            if (account.get().getStaff().getShop().getId()
                    .equals(promotion.get().getAccount().getStaff().getShop().getId())){

                promotionJPA.deleteById(id);
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }
}
