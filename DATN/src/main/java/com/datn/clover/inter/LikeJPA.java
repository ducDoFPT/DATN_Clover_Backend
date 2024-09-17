package com.datn.clover.inter;

import com.datn.clover.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeJPA extends JpaRepository<Like, String> {
}
