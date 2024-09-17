package com.datn.clover.inter;

import com.datn.clover.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJPA extends JpaRepository<Comment, String> {
}
