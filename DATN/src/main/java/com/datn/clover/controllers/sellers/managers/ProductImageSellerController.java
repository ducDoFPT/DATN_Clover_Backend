package com.datn.clover.controllers.sellers.managers;

import com.datn.clover.JPAs.AccountSellerJPA;
import com.datn.clover.JPAs.ProductImageSellerJPA;
import com.datn.clover.JPAs.ProductSellerJPA;
import com.datn.clover.entity.Account;
import com.datn.clover.entity.ProdImage;
import com.datn.clover.entity.Product;
import com.datn.clover.inter.AccountJPA;
import com.datn.clover.services.sellers.UploadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seller/productimage")
public class ProductImageSellerController {
    @Autowired
    ProductImageSellerJPA productImageSeller;
    @Autowired
    private UploadServices uploadServices;
    @Autowired
    private ProductImageSellerJPA productImageSellerJPA;
    @Autowired
    private ProductSellerJPA productSellerJPA;
    @Autowired
    private AccountJPA accountJPA;
    @Autowired
    private AccountSellerJPA accountSellerJPA;

    //them anh san pham
    @PostMapping("/createImageProduct")
    public ResponseEntity<?> createImageProduct(@RequestParam("file") List<MultipartFile> images, @RequestParam("productID") String productID){
        Optional<Product> product = productSellerJPA.findById(productID);
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ProdImage prodImage = new ProdImage();
        int i = 5;
        for (MultipartFile file : images) {
            String fileName = uploadServices.uploadFile(file);
            prodImage.setId("I00"+i);
            prodImage.setProd(product.get());
            prodImage.setName(fileName);
            productImageSellerJPA.save(prodImage);
            i++;
        }
        return ResponseEntity.ok().build();
    }
    //cap nhat anh san pham
    @PutMapping("/updateImageProduct/{token}")
    public ResponseEntity<?> updateImageProduct(@PathVariable("token") String token, List<MultipartFile> images, @RequestParam("productID") String productID){
        Optional<Product> product = productSellerJPA.findById(productID);
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<ProdImage> productImages = productImageSellerJPA.findByProductId(product.get().getId());
        ProdImage prodImage = new ProdImage();
        for (MultipartFile file : images) {
            for(ProdImage proImage : productImages){
                if(!proImage.getName().equals(file.getOriginalFilename())){
                    String fileName = uploadServices.uploadFile(file);
                    prodImage.setProd(product.get());
                    prodImage.setName(fileName);
                    productImageSellerJPA.save(prodImage);
                }
            }
        }
        return ResponseEntity.ok().build();
    }
  //xoa anh san pham
    @DeleteMapping("/deleteImageProduct/{token}")
    public ResponseEntity<Void> deleteImageProduct(@PathVariable("token") String token, @RequestParam("productID") String productID){
        try {
        Optional<Product> product = productSellerJPA.findById(productID);
        Optional<Account> account = accountSellerJPA.getAccountByUsername(token);
        if (product.isEmpty() || account.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<ProdImage> productImages = productImageSellerJPA.findByProductId(product.get().getId());
            for (ProdImage prodImage : productImages) {
                    productImageSellerJPA.deleteById(prodImage.getId());
            }
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
