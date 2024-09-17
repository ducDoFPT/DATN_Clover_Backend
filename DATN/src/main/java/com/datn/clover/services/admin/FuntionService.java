package com.datn.clover.services.admin;

import com.datn.clover.entity.Function;
import com.datn.clover.inter.FuntionJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuntionService {

    @Autowired
    private FuntionJPA funtionJPA;

    public List<Function> getAllFuntions() {
        return funtionJPA.findAll();
    }
}
