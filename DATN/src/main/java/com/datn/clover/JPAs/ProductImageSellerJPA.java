package com.datn.clover.JPAs;

import com.datn.clover.entity.ProdImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageSellerJPA extends JpaRepository<ProdImage, String > {
    @Query("select i from ProdImage i where  i.prod.id = :id")
    List<ProdImage> findByProductId(@Param("id") String id);
}
