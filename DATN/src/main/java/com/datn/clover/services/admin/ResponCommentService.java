package com.datn.clover.services.admin;

import com.datn.clover.entity.RespondComment;
import com.datn.clover.inter.RespondCommentJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponCommentService {

    @Autowired
    private RespondCommentJPA respondCommentJPA;

    public List<RespondComment> getAllRespondComments() {
        return respondCommentJPA.findAll();
    }
}
