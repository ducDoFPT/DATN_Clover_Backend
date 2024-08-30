package com.datn.clover.responeObject;

import com.datn.clover.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponse {
    private String id;

    private String content;

    private Instant commentDay;

    private PostResponse post;

    private Account account;

    private Set<RespondCommentResponse> respondComments = new LinkedHashSet<>();

}