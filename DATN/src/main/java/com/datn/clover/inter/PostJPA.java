package com.datn.clover.inter;

import com.datn.clover.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJPA extends JpaRepository<Post, Integer> {
}
