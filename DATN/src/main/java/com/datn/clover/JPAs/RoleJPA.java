package com.datn.clover.JPAs;

import com.datn.clover.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJPA extends JpaRepository<Role, String> {
    @Query("select r from Role r where r.name = :name")
    Role findByName(@Param("name") String name);
}
