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
public class PostResponse {
    private String id;

    private String title;

    private Instant postDay;

    private String content;

    private Integer numberLikes;

    private String status;

    private String stypeShare;

    private Account account;

    private Set<CommentResponse> comments = new LinkedHashSet<>();

    private Set<LikeResponse> likes = new LinkedHashSet<>();

    private Set<PostImageResponse> postImages = new LinkedHashSet<>();

    private Set<ShareResponse> shares = new LinkedHashSet<>();

    private Set<StorageResponse> storages = new LinkedHashSet<>();

}