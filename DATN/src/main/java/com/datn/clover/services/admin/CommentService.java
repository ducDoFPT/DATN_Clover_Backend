package com.datn.clover.services.admin;

import com.datn.clover.entity.Comment;
import com.datn.clover.inter.CommentJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentJPA commentJPA;

    public List<Comment> getAllComments() {
        return commentJPA.findAll();
    }
}
