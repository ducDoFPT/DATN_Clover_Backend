package com.datn.clover.responeObject;

import com.datn.clover.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class PostResponse {
    private Integer id;

    private String title;

    private Instant postDay;

    private String content;

    private Integer numberLikes;

    private String stypeShare;

    @JsonIgnoreProperties({"posts"})
    private PostStatus status;

    @JsonIgnoreProperties({"post", "account", "respondComments"})
    private Set<Comment> comments = new LinkedHashSet<>();

    @JsonIgnoreProperties({"post", "account"})
    private Set<Like> likes = new LinkedHashSet<>();

    @JsonIgnoreProperties({"post"})
    private Set<PostImage> postImages = new LinkedHashSet<>();

    @JsonIgnoreProperties({"post", "account"})
    private Set<Share> shares = new LinkedHashSet<>();

    @JsonIgnoreProperties({"post", "account"})
    private Set<Storage> storages = new LinkedHashSet<>();

    @JsonIgnoreProperties({"role", "status", "notifications"
            , "addresses", "bills", "carts"
            , "comments", "evaluates", "interacts"
            , "likes", "posts", "promotions"
            , "respondComments", "shares", "shipBills"
            , "shops", "staff", "storages"
            , "vouchers", "accounts", "account"
            , "acc", "follows1", "follows2"
            , "friends1", "friends2","password", "username"})
    private Account account;
}
