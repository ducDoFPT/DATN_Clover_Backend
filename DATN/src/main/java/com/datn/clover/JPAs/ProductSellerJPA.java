package com.datn.clover.JPAs;

import com.datn.clover.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProductSellerJPA extends JpaRepository<Product, String> {
    @Query("select p from Product p where p.shop.name = :shopName")
    ArrayList<Product> findProductByShop(@Param("shopName") String shopName);
}
