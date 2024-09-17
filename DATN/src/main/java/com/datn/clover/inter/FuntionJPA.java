package com.datn.clover.inter;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.Function;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuntionJPA extends JpaRepository<Function, String> {
}
