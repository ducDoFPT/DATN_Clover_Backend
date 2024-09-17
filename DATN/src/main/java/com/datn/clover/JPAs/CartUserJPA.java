package com.datn.clover.JPAs;

import com.datn.clover.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartUserJPA extends JpaRepository<Cart, String> {
    @Query("select c from Cart c where c.account.username = :username")
    Cart findCartByAccount(@Param("username") String username);
}
