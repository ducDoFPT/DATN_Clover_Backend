package com.datn.clover.responeObject;

import com.datn.clover.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class RespondCommentResponse {
    private String id;

    private String content;

    private Instant respondDay;

    private CommentResponse comment;

    private Account account;

}