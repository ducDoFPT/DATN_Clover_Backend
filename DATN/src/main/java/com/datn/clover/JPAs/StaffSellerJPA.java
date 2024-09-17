package com.datn.clover.JPAs;

import com.datn.clover.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffSellerJPA extends JpaRepository<Staff, String> {
    @Query("select s from Staff s where s.account.username = :username")
    Optional<Staff> findByUsername(@Param("username") String username);
}
