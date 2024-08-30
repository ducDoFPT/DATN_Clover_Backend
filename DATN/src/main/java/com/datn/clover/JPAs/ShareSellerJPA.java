package com.datn.clover.JPAs;

import com.datn.clover.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareSellerJPA extends JpaRepository<Share, String> {
}
