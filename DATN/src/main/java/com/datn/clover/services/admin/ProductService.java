package com.datn.clover.services.admin;

import com.datn.clover.entity.Product;
import com.datn.clover.inter.ProductJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductJPA productJPA;

    public List<Product> getAllProducts() {
        return productJPA.findAll();
    }
}
