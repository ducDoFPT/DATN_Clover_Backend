package com.datn.clover.services.admin;

import com.datn.clover.entity.Like;
import com.datn.clover.inter.LikeJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeJPA likeJPA;

    public List<Like> getAllLikes() {
        return likeJPA.findAll();
    }
}
