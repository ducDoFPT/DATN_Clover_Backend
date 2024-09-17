package com.datn.clover.inter;

import com.datn.clover.entity.DetailBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetaillBillJPA extends JpaRepository<DetailBill, String> {
    // lay detail bill bang shop va id bill
    @Query("select d from DetailBill d where d.bill.id = :id and d.prod.shop.id = :idShop")
    List<DetailBill> findByBillIdShopID(@Param("id") String id, @Param("idShop") String idShop);

    @Query("select d from DetailBill d where d.prod.shop.id = :idShop")
    List<DetailBill> findByShopID( @Param("idShop") String idShop);
}
