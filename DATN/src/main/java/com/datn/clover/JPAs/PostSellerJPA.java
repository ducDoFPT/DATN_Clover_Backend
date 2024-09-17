package com.datn.clover.JPAs;

import com.datn.clover.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface PostSellerJPA extends JpaRepository<Post, String> {

        // get post by username of account
        @Query("select p from Post p where p.account.username = :username")
        Optional<List<Post>> findByUsername(@Param("username") String username);
        @Transactional
        @Modifying
        @Query("delete PostImage p where p.post.id = :id")
        void deletePostImage(@Param("id") String id);


}
