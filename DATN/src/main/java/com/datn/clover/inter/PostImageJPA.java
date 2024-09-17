package com.datn.clover.inter;

import com.datn.clover.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageJPA extends JpaRepository<PostImage, String> {
}
