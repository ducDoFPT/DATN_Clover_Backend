package com.datn.clover.services.admin;

import com.datn.clover.entity.Role;
import com.datn.clover.inter.RoleJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleJPA roleJPA;

    public List<Role> getAllRoles() {
        return roleJPA.findAll();
    }
}
