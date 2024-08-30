package com.datn.clover.JPAs;

import com.datn.clover.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillUserJPA extends JpaRepository<Bill, String> {
    @Query("select b from Bill b where  b.account.username = :username")
    List<Bill> findByAccount(@Param("username") String username);
}
