package com.datn.clover.JPAs;

import com.datn.clover.entity.Evaluate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluateUserJPA extends JpaRepository<Evaluate, String> {
   @Query("select e from Evaluate e where e.acc.username = :username")
    Optional<List<Evaluate>> getEvaluateByUsername(@Param("username") String username);

   @Query("select e from Evaluate e where e.prod.shop.id = :id")
    Optional<List<Evaluate>> getEvaluateByShopId(@Param("id") String id);
//   @Transactional
//   @Modifying
//   @Query(""
}
