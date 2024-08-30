package com.datn.clover.inter;

import com.datn.clover.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJPA extends JpaRepository<Account, String> {
}
