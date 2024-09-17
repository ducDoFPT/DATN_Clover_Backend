package com.datn.clover.inter;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJPA extends JpaRepository<Role, String> {
}
