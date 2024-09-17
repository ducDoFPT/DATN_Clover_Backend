package com.datn.clover.inter;

import com.datn.clover.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendJPA extends JpaRepository<Friend, String> {
}
