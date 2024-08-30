package com.datn.clover.JPAs;

import com.datn.clover.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostImageJPA extends JpaRepository<PostImage, String> {
    @Query("select i from PostImage i where  i.post.id = :id")
    List<PostImage> findByPostId(@Param("id") String id);
}
