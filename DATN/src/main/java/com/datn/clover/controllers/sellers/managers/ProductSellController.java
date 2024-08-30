package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.Bean.Sellers.ProductSellerBean;
import com.datn.clover.JPAs.*;

import com.datn.clover.entity.*;
//import com.datn.clover.services.sellers.JwtService;
import com.datn.clover.services.sellers.UploadServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.BindException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sell/product")
public class ProductSellController {
    @Autowired
    ProductSellerJPA productJPA;

//    @Autowired
//    JwtService jwtService;

    @Autowired
    AccountSellerJPA accountJPA;

//    @Autowired
//    private ShopJPA shopJPA;
    @Autowired
    private TypeProductJPA typeProductJPA;
    @Autowired
    private PromotionJPA promotionJPA;
    @Autowired
    private UploadServices uploadServices;
    @Autowired
    private ProductImageSellerJPA productImageJPA;
    @Autowired
    private ShopJPA shopJPA;
    @Autowired
    private StaffJPA staffJPA;

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
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productJPA.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getProductBySeller/{token}")
    public ResponseEntity<List<Product>> getProductByShop(@PathVariable String token) {
//        String ussername = jwtService.extractUsername(token);
        Optional<Account> account = accountJPA.getAccountByUsername(token);
        Shop listShopByAcount = shopJPA.findShopsByAccount(account.get().getUsername());
        List<Product> products = productJPA.findProductByShop(listShopByAcount.getName());
        return ResponseEntity.ok(products);
    }

    @PostMapping("/create/{token}")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductSellerBean product,
                                              BindingResult error,

                                              @PathVariable("token") String token) throws BindException {
        if (error.hasErrors()) {
            throw new BindException(error);
        }
        try {
//        String ussername = jwtService.extractUsername(token);
        Optional<Account> account = accountJPA.getAccountByUsername(token);
            Optional<Staff> staff = staffJPA.findByUsername(token);
            System.out.println(staff.isPresent());
            Optional<Shop> shop = shopJPA.findById(staff.get().getShop().getId());
        if (account.isPresent() && shop.isPresent()) {
            Product pro = new Product();
            pro.setId(product.getId());
            pro.setName(product.getName());
            pro.setDescription(product.getDescription());
            pro.setPrice(product.getPrice());
            pro.setQuantity(product.getQuantity());
            pro.setShop(shop.get());
            TypeProduct typeProduct = typeProductJPA.findById(product.getProdTypeID()).get();
            Optional<Promotion> promotion = promotionJPA.findById(product.getPromotionID());
            pro.setProdType(typeProduct);
            pro.setPromotion(promotion.get());
            pro.setShop(shop.get());
            Product proRS = productJPA.save(pro);

            return ResponseEntity.ok(proRS);
        }
        return ResponseEntity.notFound().build();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{token}")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid ProductSellerBean product,
                                                 @PathVariable("token") String token){
//            String ussername = jwtService.extractUsername(token);
            Optional<Account> account = accountJPA.getAccountByUsername(token);
            if (account.isPresent()) {
                Product pro = productJPA.findById(product.getId()).get();
                pro.setName(product.getName());
                pro.setDescription(product.getDescription());
                pro.setPrice(product.getPrice());
                pro.setQuantity(product.getQuantity());
                //lấy type và khuyến mãi
                TypeProduct typeProduct = typeProductJPA.findById(product.getProdTypeID()).get();
                Optional<Promotion> promotion = promotionJPA.findById(product.getPromotionID());
                pro.setProdType(typeProduct);
                pro.setPromotion(promotion.get());
                //luư sản phẩm
               Product proRS = productJPA.save(pro);

                return ResponseEntity.ok(proRS);
            }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public boolean deleteProduct(@RequestParam("id") String id) {
        try {
            Product pro = productJPA.findById(id).get();
            productJPA.delete(pro);
            return true;
        }catch (Exception _){
            return false;
        }
    }


}
