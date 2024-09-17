package com.datn.clover.inter;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageJPA extends JpaRepository<Storage, String> {
}
