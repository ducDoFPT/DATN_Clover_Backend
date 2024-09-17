package com.datn.clover.inter;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyJPA extends JpaRepository<Property, String> {
}
