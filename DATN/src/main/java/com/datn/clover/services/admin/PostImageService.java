package com.datn.clover.services.admin;

import com.datn.clover.entity.PostImage;
import com.datn.clover.inter.PostImageJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostImageService {

    @Autowired
    private PostImageJPA postImageJPA;

    public List<PostImage> getAllPostImages() {
        return postImageJPA.findAll();
    }
}
