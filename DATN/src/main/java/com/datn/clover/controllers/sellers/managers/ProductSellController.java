package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.DTO.Sellers.ProductSellerBean;
import com.datn.clover.JPAs.*;

import com.datn.clover.entity.*;
//import com.datn.clover.services.sellers.JwtService;
import com.datn.clover.mapper.ProductSellerMapper;
import com.datn.clover.responeObject.ProductSellerResponse;
import com.datn.clover.services.JwtService;
import com.datn.clover.services.sellers.ProductSellerServices;
import com.datn.clover.services.sellers.UploadSellerServices;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/sell/product")
public class ProductSellController {
    private static final Logger log = LoggerFactory.getLogger(ProductSellController.class);
    @Autowired
    ProductSellerJPA productJPA;

//    @Autowired
//    JwtService jwtService;

    @Autowired
    AccountSellerJPA accountJPA;

//    @Autowired
//    private ShopSellerJPA shopJPA;
    @Autowired
    private TypeProductSellerJPA typeProductJPA;
    @Autowired
    private PromotionSellerJPA promotionJPA;
    @Autowired
    private UploadSellerServices uploadServices;
    @Autowired
    private ProductImageSellerJPA productImageJPA;
    @Autowired
    private ShopSellerJPA shopJPA;
    @Autowired
    private StaffSellerJPA staffJPA;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ProductSellerMapper productSellerMapper;
    @Autowired
    private ProductSellerServices productSellerServices;
    @Autowired
    Validator validator;

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
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productJPA.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getProductBySeller")
    public ResponseEntity<List<Product>> getProductByShop(@RequestHeader("Authorization") String token) {
//        String ussername = jwtService.extractUsername(token);
        Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
        Shop listShopByAcount = shopJPA.findShopsByAccount(account.get().getUsername());
        List<Product> products = productJPA.findProductByShop(listShopByAcount.getName());
        return ResponseEntity.ok(products);
    }

    @PostMapping("/create")
    public ResponseEntity<?> addProduct(@RequestParam Map<String, String> params,
                                              @RequestHeader("Authorization") String token,
                                              @RequestParam("file") List<MultipartFile> images) throws BindException {
        ProductSellerBean product = productSellerMapper.toDTO(params);
        BindingResult error = new BeanPropertyBindingResult(product, "product");
        validator.validate(product,error);
        if (error.hasErrors()) {
            throw new BindException( error);
        }
        try {
//        String ussername = jwtService.extractUsername(token);
        Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
            Optional<Staff> staff = staffJPA.findByUsername(jwtService.accessToken(token));
            Optional<Shop> shop = shopJPA.findById(staff.get().getShop().getId().toString());
        if (account.isPresent() && shop.isPresent() && account.get().getShops().getId().equals(account.get().getStaff().getShop().getId()))
        {
            return productSellerServices.create(product, images, shop.get());
        }
        return ResponseEntity.notFound().build();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestParam Map<String, String> params,
                                                 @RequestHeader("Authorization") String token,
                                                 @RequestParam("file") List<MultipartFile> images) throws BindException {
;
        try {
        ProductSellerBean product = productSellerMapper.toDTO(params);
        BindingResult error = new BeanPropertyBindingResult(product, "product");
       validator.validate(product,error);
        if (error.hasErrors()) {
            throw new BindException( error);
        }
            Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
            if (account.isPresent() ) {
              return productSellerServices.update(product, images);
            }
        return ResponseEntity.notFound().build();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam("id") String id, @RequestHeader("Authorization") String token) {
        Map<String, String> rs = new HashMap<>();
        try {
            Optional<Product> pro = productJPA.findById(id);
            Optional<Account> account = accountJPA.getAccountByUsername(jwtService.accessToken(token));
            if (pro.isPresent() && account.isPresent()
                    && account.get().getStaff().getShop().getId().equals(pro.get().getShop().getId())) {
                productJPA.deleteProductImage(pro.get().getId().toString());
                productJPA.delete(pro.get());
                rs.put("result", "Success");
                return ResponseEntity.ok().body(rs);
            }
            rs.put("result", "Fail");
            return ResponseEntity.badRequest().body(rs);
        }catch (Exception e){
            rs.put("result", "Fail");
            e.printStackTrace();
            return ResponseEntity.badRequest().body(rs);
        }
    }
}
