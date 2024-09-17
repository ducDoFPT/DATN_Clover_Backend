package com.datn.clover.services.admin;

import com.datn.clover.entity.Interact;
import com.datn.clover.inter.InteractJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractService {

    @Autowired
    private InteractJPA interactJPA;

    public List<Interact> getAllInteracts() {
        return interactJPA.findAll();
    }
}
