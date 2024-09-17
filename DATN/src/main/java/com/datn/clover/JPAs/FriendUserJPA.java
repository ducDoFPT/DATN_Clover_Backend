package com.datn.clover.JPAs;

import com.datn.clover.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendUserJPA extends JpaRepository<Friend, String> {
    @Query("select f from Friend f where f.accountId1.username = :username")
    List<Friend> findByAccountId1(@Param("username") String username);
}
