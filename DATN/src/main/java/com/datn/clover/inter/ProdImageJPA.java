package com.datn.clover.inter;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.ProdImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdImageJPA extends JpaRepository<ProdImage, String> {
}
