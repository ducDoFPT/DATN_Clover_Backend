package com.datn.clover.JPAs;

import com.datn.clover.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherSellerJPA extends JpaRepository<Voucher,String> {
    @Query(value = "SELECT v.* FROM vouchers v INNER JOIN accounts a ON a.id = v.account_id INNER JOIN staff q ON a.id = q.account_id INNER JOIN shops s ON s.id = q.shop_id WHERE s.id = :id\n ", nativeQuery = true)
    List<Voucher> findByAccount( @Param("id") String id);
}
