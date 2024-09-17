package com.datn.clover.inter;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.PropertiesValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertiesValueJPA extends JpaRepository<PropertiesValue, String> {
}
