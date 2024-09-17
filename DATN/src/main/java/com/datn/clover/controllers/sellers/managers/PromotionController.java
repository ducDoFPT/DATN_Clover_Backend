package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.DTO.Sellers.PromotionSellerBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.PromotionSellerJPA;
import com.datn.clover.JPAs.ShopSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Promotion;
import com.datn.clover.mapper.PromotionMapper;
import com.datn.clover.services.JwtService;
import com.datn.clover.services.sellers.PromotionSellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/seller/promotion")
public class PromotionController {
    @Autowired
    PromotionSellerJPA promotionJPA;
    @Autowired
    private AccountSellerJPA accountJPA;
    @Autowired
    private ShopSellerJPA shopJPA;
    @Autowired
    private PromotionSellerService promotionService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PromotionMapper promotionMapper;
    @Autowired
    Validator validator;
    //handle error DTO
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBindException(BindingResult be) {
        // Trả về message của lỗi đầu tiên
        Map<String, String> errors = new HashMap<>();
        String errorMessage = "Request không hợp lệ";
        errors.put("error", errorMessage);
        if (be.hasErrors()) {
            errorMessage = be.getAllErrors().getFirst().getDefaultMessage();
            errors.put("message", errorMessage);
        }
        return errors;
    }

    @GetMapping("/getAllPromotionByShop")
    public List<Promotion> getAllPromtionByShop(@RequestHeader("Authorization") String token) {
        Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
        return account.map(value -> promotionJPA.findByAccount(value.getStaff().getShop().getId().toString())).orElse(null);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPromotion(@RequestHeader("Authorization") String token, @RequestParam Map<String, String> params) {
        try {
            PromotionSellerBean promotionSellerBean = promotionMapper.toDTO(params);
            BindingResult error = new BeanPropertyBindingResult(promotionSellerBean, "promotionSellerBean");
            validator.validate(promotionSellerBean,error);
            if (error.hasErrors()) {
                throw new BindException( error);
            }
            if (promotionSellerBean.getStartDay().isBefore(Instant.now())  || promotionSellerBean.getEndDay().isBefore(Instant.now()) || promotionSellerBean.getStartDay().isAfter(promotionSellerBean.getEndDay())) {
                throw new BindException( error);
            }
            Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
            return account.map(value -> ResponseEntity.ok(promotionService.savePromotion(promotionSellerBean, promotionJPA, value))).orElseGet(() -> ResponseEntity.notFound().build());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePromotion(@RequestHeader("Authorization") String token, @RequestParam Map<String , String> params)  throws BindException {
        PromotionSellerBean promotionSellerBean = promotionMapper.toDTO(params);
        BindingResult error = new BeanPropertyBindingResult(promotionSellerBean, "promotionSellerBean");
        validator.validate(promotionSellerBean,error);
        if (error.hasErrors()) {
            throw new BindException( error);
        }
        try {
            Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
            if (account.isPresent()) {
                return account.map(value -> ResponseEntity.ok(promotionService.updatePromotion(promotionSellerBean, promotionJPA, value))).orElseGet(() -> ResponseEntity.notFound().build());
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deletePromotion(@RequestHeader("Authorization") String token,@RequestParam("id") String id) {
        try {
            Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
            if (account.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            Optional<Promotion> promotion = promotionJPA.findById(id);
            if (promotion.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            if (account.get().getStaff().getShop().getId()
                    .equals(promotion.get().getAccount().getStaff().getShop().getId())){
                promotionJPA.deleteById(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
