package com.datn.clover.JPAs;

import com.datn.clover.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeUserJPA extends JpaRepository<Like, String> {
    @Query("select l from Like l where  l.post.id = :id")
    List<Like> findLikeByPostId(@Param("id") String id);
}
