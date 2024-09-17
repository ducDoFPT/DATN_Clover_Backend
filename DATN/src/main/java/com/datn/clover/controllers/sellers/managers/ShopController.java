package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.DTO.Sellers.ShopSellerBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.ShopSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Shop;
import com.datn.clover.mapper.ShopMapper;
import com.datn.clover.responeObject.ShopSellerResponse;
import com.datn.clover.services.JwtService;
import com.datn.clover.services.sellers.ShopSellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/shop")
public class ShopController {
    @Autowired
    ShopSellerJPA shopJPA;
    @Autowired
    private AccountSellerJPA accountSellerJPA;
    @Autowired
    private ShopSellerService shopSellerService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ShopMapper shopMapper;
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

    @GetMapping("/getShopByAcount")
    public Shop getShopByAcount(@RequestHeader("Authorization") String token) {
        try {
            return shopJPA.findShopsByAccount(jwtService.accessToken(token));
        }catch (Exception e){
            return new Shop();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ShopSellerResponse> createShop(@RequestParam Map<String, String> params, @RequestHeader("Authorization") String token) throws BindException {
            ShopSellerBean shop = shopMapper.toDTO(params);
            BindingResult error = new BeanPropertyBindingResult(shop, "shop");
            validator.validateObject(shop);
                if (error.hasErrors()) {
            throw new BindException(error);  // Ném ngoại lệ để được xử lý bởi handleBindException
        }
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
            if (account.isEmpty() ) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            Shop shops = shopJPA.findShopsByAccount(account.get().getUsername());
            if (shops != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            Shop createdShop = shopSellerService.createShop(shop,account.get());
            return ResponseEntity.ok(shopSellerService.setResponse(createdShop));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ShopSellerResponse> updateShop(@RequestParam Map<String, String> params, @RequestHeader("Authorization") String token, @RequestParam("id") String id) throws BindException {
        ShopSellerBean shop = shopMapper.toDTO(params);
        BindingResult error = new BeanPropertyBindingResult(shop, "shop");
        validator.validateObject(shop);
        if (error.hasErrors()) {
            throw new BindException(error);  // Ném ngoại lệ để được xử lý bởi handleBindException
        }
        try {
            // chi co account trong trong moi update dc
            Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
            if (account.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            if (account.get().getId().equals(account.get().getShops().getAccount().getId())) {
                Shop rs = shopSellerService.updateShop(id,shop);
                return ResponseEntity.ok(shopSellerService.setResponse(rs));
            }
           return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete")
    public boolean deleteShop(@RequestHeader("Authorization") String token) {
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));

            if (account.isEmpty()) {
                return false;
            }
            if (account.get().getId().equals(account.get().getShops().getAccount().getId())){
                shopJPA.delete(account.get().getShops());
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }
}
