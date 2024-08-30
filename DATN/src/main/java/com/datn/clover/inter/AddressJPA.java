package com.datn.clover.inter;

import com.datn.clover.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressJPA extends JpaRepository<Address, String> {
}
