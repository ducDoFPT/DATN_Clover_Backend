package com.datn.clover.JPAs;

import com.datn.clover.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillJSellerPA extends JpaRepository<Bill, String> {
    @Query(value = "select b.* from bills b inner join detail_bills d on b.id = d.bill_id inner join products p on p.id = d.prod_id inner join shops s on s.id = p.shop_id where s.id = :id",nativeQuery = true)
    List<Bill> getAllBillByShop(@Param("id") String id);
}
