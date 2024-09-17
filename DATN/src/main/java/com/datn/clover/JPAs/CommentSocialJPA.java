package com.datn.clover.JPAs;

import com.datn.clover.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentSocialJPA extends JpaRepository<Comment, String> {
    @Query("SELECT c from Comment c where  c.post.id = :id")
    List<Comment> findByPostId(@Param("id") String id);
}
