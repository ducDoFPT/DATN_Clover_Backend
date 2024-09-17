package com.datn.clover.JPAs;

import com.datn.clover.entity.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostStatusJPA extends JpaRepository<PostStatus, String> {

}
