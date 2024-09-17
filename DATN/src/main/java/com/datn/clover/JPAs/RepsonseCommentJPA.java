package com.datn.clover.JPAs;

import com.datn.clover.entity.RespondComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepsonseCommentJPA extends JpaRepository<RespondComment, String> {
}
