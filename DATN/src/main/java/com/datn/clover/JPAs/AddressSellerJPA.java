package com.datn.clover.JPAs;

import com.datn.clover.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressSellerJPA extends JpaRepository<Address, String> {
    @Query("select a from Address a where a.account.username = :username")
    Optional<Address> findByUsername(@Param("username") String username);
}
