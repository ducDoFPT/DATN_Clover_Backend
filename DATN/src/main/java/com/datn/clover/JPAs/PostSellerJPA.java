package com.datn.clover.JPAs;

import com.datn.clover.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PostSellerJPA extends JpaRepository<Post, String> {

        // get post by username of account
        @Query("select p from Post p where p.account.username = :username")
        Optional<List<Post>> findByUsername(@Param("username") String username);



}
