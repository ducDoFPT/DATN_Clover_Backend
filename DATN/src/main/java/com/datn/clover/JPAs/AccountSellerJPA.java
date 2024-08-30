package com.datn.clover.JPAs;

import com.datn.clover.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountSellerJPA extends JpaRepository<Account, String> {
        // get all query Accounts
        @Query("select a from Account a")
        Optional<List<Account>> getAllAccounts();
        //get account by username
        @Query("select a from Account a where a.username = :username")
        Optional<Account> getAccountByUsername(@Param("username") String username);
        //get Account by phone
        @Query("select a from Account a where a.phone = :phone")
        Optional<Account> getAccountByPhone(@Param("phone") String phone);

        @Query(value = "select * from accounts c where c.username != :id and c.phone = :phone ", nativeQuery = true)
        List<Account> getAllClientNotExistsByPhone(@Param("id") String id, @Param("phone") String phone );
        @Query(value = "select * from accounts c where c.username != :id and c.email = :email ", nativeQuery = true)
        List<Account> getAllClientNotExistsByEmail(@Param("id") String id, @Param("email") String email );
        @Query("select c from Account c  where c.email = :email")
        Optional<Account> getClientByEmail(@Param("email") String email);

        @Query("select c from Account c  where c.phone = :phone")
        Optional<Account> getClientByPhone(@Param("phone") String phone);

        @Query("select a from Account a where a.phone = :check or a.email = :check")
        Optional<Account> getClientByEmailOrPhone(@Param("check") String check);
}
