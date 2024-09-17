package com.datn.clover.services.admin;

import com.datn.clover.entity.Follow;
import com.datn.clover.inter.FollowJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {

    @Autowired
    private FollowJPA followJPA;

    public List<Follow> getAllFollows() {
        return followJPA.findAll();
    }
}
