package com.datn.clover.JPAs;

import com.datn.clover.entity.ProdCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCarUserJPA extends JpaRepository<ProdCart, String> {
    @Query("select c from ProdCart c where  c.cart.account.username = :username")
    List<ProdCart> findByCartId(@Param("username") String id);
}
