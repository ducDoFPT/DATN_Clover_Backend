package com.datn.clover.controllers.user.shopping;

import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.CartJPA;
import com.datn.clover.JPAs.ProductCartJPA;
import com.datn.clover.JPAs.ProductSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.Cart;
import com.datn.clover.entity.ProdCart;
import com.datn.clover.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private CartJPA cartJPA;
    @Autowired
    private ProductCartJPA productCartJPA;

    @GetMapping
    public List<Product> getProducts() {
        return productJPA.findAll();
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Optional<Product> product = productJPA.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addToCart/{token}")
    public ResponseEntity<ProdCart> postAddToCart(@RequestParam("quantity") Optional<Integer> quantity,
                                              @PathVariable("token") Optional<String> username,
                                              @RequestParam("id") Optional<String> idProd,
                                              @RequestParam("cartProID") String cartID) {
        Optional<Account> client = accountJPA.getAccountByUsername(username.get());
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
            prodCart.setId(cartID);
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
