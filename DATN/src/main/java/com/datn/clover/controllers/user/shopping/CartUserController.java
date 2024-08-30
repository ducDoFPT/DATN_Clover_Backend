package com.datn.clover.controllers.user.shopping;

import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.CartJPA;
import com.datn.clover.JPAs.ProductCartJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Cart;
import com.datn.clover.entity.ProdCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/shopping/cart")
public class CartUserController {
    @Autowired
    CartJPA cartJPA;
    @Autowired
    private ProductCartJPA productCartJPA;
    @Autowired
    private AccountSellerJPA accountSellerJPA;

    @GetMapping("/{username}")
    public List<ProdCart> getCart(@PathVariable String username) {
        try {
            return productCartJPA.findByCartId(username);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    @PostMapping("/createCart/{token}")
    public ResponseEntity<Cart> createCart(@PathVariable String token, @RequestParam("id")String id) {
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(token);
            if (account.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            Cart cart = new Cart();
            cart.setId(id);
            cart.setAccount(account.get());
            return ResponseEntity.ok(cartJPA.save(cart));
        }catch (Exception e){
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/deleteCart/{id}")
    public Boolean deleteCart( @PathVariable("id") String id) {
        try {
            productCartJPA.deleteById(id);
        }catch (Exception e) {
            return false;
        }
        return true;
    }


    @PutMapping("/increase/{id}")
    public ResponseEntity<ProdCart> increaseCart( @PathVariable("id") String id) {
        Optional<ProdCart> pro = productCartJPA.findById(id);
        if (pro.isPresent()) {
            pro.get().setQuantity(pro.get().getQuantity() + 1);
            productCartJPA.save(pro.get());
            return ResponseEntity.ok(pro.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/decrease/{id}")
    public ResponseEntity<ProdCart> decreaseCart( @PathVariable("id") String id) {
        Optional<ProdCart> pro = productCartJPA.findById(id);
        if (pro.isPresent()) {
            if (pro.get().getQuantity() > 1) {
            pro.get().setQuantity(pro.get().getQuantity() - 1);
            productCartJPA.save(pro.get());
            }
            return ResponseEntity.ok(pro.get());
        }
        return ResponseEntity.notFound().build();
    }
}
