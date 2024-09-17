package com.datn.clover.services.admin;

import com.datn.clover.entity.Evaluate;
import com.datn.clover.inter.EvaluateJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateService {

    @Autowired
    private EvaluateJPA evaluateJPA;

    public List<Evaluate> getAllEvaluates() {
        return evaluateJPA.findAll();
    }
}
