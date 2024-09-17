package com.datn.clover.inter;

import com.datn.clover.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareJPA extends JpaRepository<Share, String> {
}
