package com.datn.clover.JPAs;

import com.datn.clover.entity.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeProductJPA extends JpaRepository<TypeProduct, String> {
}
