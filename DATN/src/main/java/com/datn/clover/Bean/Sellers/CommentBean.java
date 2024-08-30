package com.datn.clover.Bean.Sellers;

import com.datn.clover.entity.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CommentBean {
    private String id;

    private String content;

    private Instant commentDay;

    private PostBean post;

    private Account account;

    private Set<RespondCommentBean> respondComments = new LinkedHashSet<>();

}