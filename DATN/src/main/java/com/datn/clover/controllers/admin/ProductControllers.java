package com.datn.clover.controllers.admin;

import com.datn.clover.entity.Product;
import com.datn.clover.services .admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductControllers {
    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
       return productService.getAllProducts();
    }
}
