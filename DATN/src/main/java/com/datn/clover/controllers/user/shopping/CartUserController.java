package com.datn.clover.controllers.user.shopping;

import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.CartUserJPA;
import com.datn.clover.JPAs.ProductCarUserJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Cart;
import com.datn.clover.entity.ProdCart;
import com.datn.clover.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/user/shopping/cart")
public class CartUserController {
    @Autowired
    CartUserJPA cartJPA;
    @Autowired
    private ProductCarUserJPA productCartJPA;
    @Autowired
    private AccountSellerJPA accountSellerJPA;
    @Autowired
    private JwtService jwtService;
    //handle error DTO
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBindException(Errors be) {
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

    @GetMapping
    public List<ProdCart> getCart(@RequestHeader("Authorization") String token) {
        try {
            return productCartJPA.findByCartId(jwtService.accessToken(token));
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Cart> createCart(@RequestHeader("Authorization") String token) {
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
            if (account.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            Cart cart = new Cart();
            cart.setAccount(account.get());
            return ResponseEntity.ok(cartJPA.save(cart));
        }catch (Exception e){
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/delete")
    public Boolean deleteCart( @RequestParam("id") String id, @RequestHeader("Authorization") String token) {
        try {
            Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
            Optional<ProdCart> cart = productCartJPA.findById(id);
            if (account.isPresent() && cart.isPresent() && account.get().getId().equals(cart.get().getCart().getAccount().getId())) {
                productCartJPA.deleteById(id);
                return true;
            }
            return false;
        }catch (Exception e) {
            return false;
        }
    }


    @PutMapping("/increase")
    public ResponseEntity<ProdCart> increaseCart( @RequestParam("id") String id, @RequestHeader("Authorization") String token) {
       Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
        Optional<ProdCart> pro = productCartJPA.findById(id);
        if (pro.isPresent() && account.isPresent() && account.get().getId().equals(pro.get().getCart().getAccount().getId())) {
            pro.get().setQuantity(pro.get().getQuantity() + 1);
            productCartJPA.save(pro.get());
            return ResponseEntity.ok(pro.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/decrease")
    public ResponseEntity<ProdCart> decreaseCart( @RequestParam("id") String id, @RequestHeader("Authorization") String token) {
        Optional<Account> account = accountSellerJPA.getAccountByUsername(jwtService.accessToken(token));
        Optional<ProdCart> pro = productCartJPA.findById(id);
        if (pro.isPresent() && account.isPresent() && account.get().getId().equals(pro.get().getCart().getAccount().getId())) {
            if (pro.get().getQuantity() > 1) {
            pro.get().setQuantity(pro.get().getQuantity() - 1);
            productCartJPA.save(pro.get());
            }
            return ResponseEntity.ok(pro.get());
        }
        return ResponseEntity.notFound().build();
    }
}
