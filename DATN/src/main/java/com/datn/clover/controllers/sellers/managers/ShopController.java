package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.Bean.Sellers.ShopSellerBean;
import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.ShopJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Shop;
import com.datn.clover.services.sellers.ShopSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shop")
public class ShopController {
    @Autowired
    ShopJPA shopJPA;
    @Autowired
    private AccountSellerJPA accountSellerJPA;
    @Autowired
    private ShopSellerService shopSellerService;

    @GetMapping("/getShopByAcount/{username}")
    public Shop getShopByAcount(@PathVariable String username) {
        try {
            return shopJPA.findShopsByAccount(username);
        }catch (Exception e){
            return new Shop();
        }
    }

    @PostMapping("/creeat/{token}")
    public ResponseEntity<Shop> createShop(@RequestBody ShopSellerBean shop, @PathVariable String token) {
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(token);
            if (account.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            Shop createdShop = shopSellerService.createShop(shop,account.get());
            return ResponseEntity.ok(createdShop);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{token}")
    public ResponseEntity<Shop> updateShop(@RequestBody ShopSellerBean shop, @PathVariable String token, @RequestParam("id") String id) {

        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(token);
            if (account.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            if (account.get().getId().equals(account.get().getShop().getAccount().getId())) {
                Shop rs = shopSellerService.updateShop(id,shop);
                return ResponseEntity.ok(rs);
            }
           return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{token}")
    public boolean deleteShop(@PathVariable String token) {
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(token);

            if (account.isEmpty()) {
                return false;
            }
            if (account.get().getId().equals(account.get().getShop().getAccount().getId())){
                shopJPA.delete(account.get().getShop());
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }
}
