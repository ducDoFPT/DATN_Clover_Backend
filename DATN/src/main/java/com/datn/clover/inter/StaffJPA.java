package com.datn.clover.inter;

import com.datn.clover.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffJPA extends JpaRepository<Staff, String> {
}
