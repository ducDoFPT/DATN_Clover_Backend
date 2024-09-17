package com.datn.clover.JPAs;

import com.datn.clover.entity.EvaluatesFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluateFeedbackJPA extends JpaRepository<EvaluatesFeedback, String> {
    @Query("Select e from EvaluatesFeedback e where  e.evaluate.id = :id")
    List<EvaluatesFeedback> findByEvaluateId(@Param("id") String id);
}
