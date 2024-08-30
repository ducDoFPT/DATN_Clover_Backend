package com.datn.clover.JPAs;

import com.datn.clover.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeJPA extends JpaRepository<Like, String> {

}
