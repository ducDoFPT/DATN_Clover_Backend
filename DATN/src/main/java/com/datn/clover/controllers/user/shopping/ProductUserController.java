package com.datn.clover.controllers.user.shopping;

import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.CartUserJPA;
import com.datn.clover.JPAs.ProductCarUserJPA;
import com.datn.clover.JPAs.ProductSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Cart;
import com.datn.clover.entity.ProdCart;
import com.datn.clover.entity.Product;
import com.datn.clover.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/user/shopping/product")
public class ProductUserController {
    @Autowired
    ProductSellerJPA productJPA;
    @Autowired
    private AccountSellerJPA accountJPA;
    @Autowired
    private CartUserJPA cartJPA;
    @Autowired
    private ProductCarUserJPA productCartJPA;
    @Autowired
    private JwtService jwtService;

    //handle error DTO
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBindException(BindException be) {
        // Trả về message của lỗi đầu tiên
        String errorMessage = "Request không hợp lệ";
        if (be.getBindingResult().hasErrors()) {
            errorMessage = be.getBindingResult().getAllErrors().getLast().getDefaultMessage();
        }
        return errorMessage;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productJPA.findAll();
    }

    @GetMapping("/getProductById")
    public ResponseEntity<Product> getProductById(@RequestParam("id") String id) {
        Optional<Product> product = productJPA.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addToCart")
    public ResponseEntity<ProdCart> postAddToCart(@RequestParam("quantity") Optional<Integer> quantity,
                                              @RequestHeader("Authorization") String token,
                                              @RequestParam("id") Optional<String> idProd) {
        Optional<Account> client = accountJPA.getAccountByUsername(jwtService.accessToken(token));
        Product product = productJPA.findById(idProd.get()).orElseThrow(() -> new RuntimeException("Product not found"));
        Cart carts = cartJPA.findCartByAccount(client.get().getUsername());

        boolean productExistsInCart = false;
            for (ProdCart p : carts.getProdCarts()) {
                if (Objects.equals(p.getProd().getId(), product.getId())) {
                    int newQuantity = quantity.orElse(0) + p.getQuantity();
                    p.setQuantity(newQuantity);
                    return ResponseEntity.ok( saveCart(p.getProd(), p));
                }

        }

        // Create new cart with new product
       ProdCart prodCart = new ProdCart();
        prodCart.setProd(product);
        prodCart.setQuantity(quantity.orElse(0));
        prodCart.setCart(carts);
        return ResponseEntity.ok( saveCart(product, prodCart));
    }

    private ProdCart saveCart(Product prod, ProdCart cart) {
        double totalMoney = 0;

                totalMoney += prod.getQuantity() * prod.getPrice();

        cart.setIntoMoney((float) totalMoney);
        return productCartJPA.save(cart);
    }
}
