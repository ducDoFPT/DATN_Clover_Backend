package com.datn.clover.inter;

import com.datn.clover.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowJPA extends JpaRepository<Follow, String> {
}
