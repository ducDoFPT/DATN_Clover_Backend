package com.datn.clover.services.admin;

import com.datn.clover.entity.Staff;
import com.datn.clover.inter.StaffJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffJPA staffJPA;

    public List<Staff> getAllStaffs() {
        return staffJPA.findAll();
    }
}
