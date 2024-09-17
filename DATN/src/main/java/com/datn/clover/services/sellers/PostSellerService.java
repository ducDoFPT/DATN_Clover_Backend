package com.datn.clover.services.sellers;

import com.datn.clover.entity.Post;
import com.datn.clover.responeObject.PostResponse;
import org.springframework.stereotype.Service;

@Service
public class PostSellerService {
    public PostResponse setResponse(Post post){
        PostResponse response = new PostResponse();
        response.setPostDay(post.getPostDay());
        response.setStatus(post.getStatus());
        response.setContent(post.getContent());
        response.setPostImages(post.getPostImages());
        response.setTitle(post.getTitle());
        response.setAccount(post.getAccount());
        return response;
    }
}
