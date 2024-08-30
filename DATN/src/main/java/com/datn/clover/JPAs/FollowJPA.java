package com.datn.clover.JPAs;

import com.datn.clover.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowJPA extends JpaRepository<Follow, String> {
    @Query("select f from Follow f where f.accountId1.username = :username")
    List<Follow> findByAccountId1(@Param("username") String username);
}
