package com.datn.clover.services.admin;

import com.datn.clover.entity.Post;
import com.datn.clover.inter.PostJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostJPA postJPA;

    public List<Post> getAllPosts() {return postJPA.findAll();}
}
