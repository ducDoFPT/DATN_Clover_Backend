package com.datn.clover.inter;

import com.datn.clover.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountJPA extends JpaRepository<Account, String> {

}
