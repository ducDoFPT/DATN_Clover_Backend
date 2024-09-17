package com.datn.clover.inter;

import com.datn.clover.entity.Account;
import com.datn.clover.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostJPA extends JpaRepository<Post, String> {

    @Query(value = "SELECT * FROM posts WHERE status LIKE 'Chờ xét duyệt'", nativeQuery = true)
    List<Post> getAllBrowsePosts();

    @Query(value = "SELECT * FROM posts WHERE status LIKE 'Bị tố cáo'", nativeQuery = true)
    List<Post> getAllDenouncePosts();

}
