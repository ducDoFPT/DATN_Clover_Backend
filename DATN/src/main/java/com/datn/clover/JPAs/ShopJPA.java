package com.datn.clover.JPAs;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopJPA extends JpaRepository<Shop, String> {

    //get shop by username
    @Query(value = "SELECT s.*\n" +
            "FROM shops s\n" +
            "INNER JOIN Staff d ON s.id = d.shop_id\n" +
            "INNER JOIN accounts a ON a.id = d.account_id\n" +
            "WHERE a.username = :username", nativeQuery = true)
    Shop findShopsByAccount(@Param("username") String username);
}
