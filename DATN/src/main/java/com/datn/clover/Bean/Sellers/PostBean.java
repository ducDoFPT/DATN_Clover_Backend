package com.datn.clover.Bean.Sellers;

import com.datn.clover.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class PostBean {
    private String id;

    private String title;

    private Instant postDay;

    private String content;

    private Integer numberLikes;

    private String status;

    private String stypeShare;

    private Account account;

    private Set<CommentBean> comments = new LinkedHashSet<>();

    private Set<LikeBean> likes = new LinkedHashSet<>();

    private Set<PostImageBean> postImages = new LinkedHashSet<>();

    private Set<ShareBean> shares = new LinkedHashSet<>();

    private Set<StorageBean> storages = new LinkedHashSet<>();

}