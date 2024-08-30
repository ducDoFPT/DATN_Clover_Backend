package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Size(max = 150)
    @Column(name = "title", length = 150)
    private String title;

    @Column(name = "post_day")
    private LocalDateTime postDay;

    @Size(max = 225)
    @Column(name = "content", length = 225)
    private String content;

    @Column(name = "number_likes")
    private Integer numberLikes;

    @Size(max = 20)
    @Column(name = "status", length = 20)
    private String status;

    @Size(max = 20)
    @Column(name = "stype_share", length = 20)
    private String stypeShare;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties({"addresses", "account", "role"
            , "notifications", "staff", "shop"
            , "bills", "cart", "comments"
            , "evaluates", "follows1", "follows2"
            , "friends1", "friends2", "interacts"
            , "posts", "promotions", "respondComments"
            , "shares", "shipBills", "storages"
            , "vouchers", "likes"})
    private Account account;

    @OneToMany(mappedBy = "post")
    @JsonIgnoreProperties({"account", "post", "respondComments"})
    private List<Comment> comments;

    @OneToMany(mappedBy = "post")
    @JsonIgnoreProperties({"post", "account"})
    private List<Like> likes;

    @OneToMany(mappedBy = "post")
    @JsonIgnoreProperties("post")
    private List<PostImage> postImages;

    @OneToMany(mappedBy = "post")
    @JsonIgnoreProperties({"post", "account"})
    private List<Share> shares;

    @OneToMany(mappedBy = "post")
    @JsonIgnoreProperties({"post", "account"})
    private List<Storage> storages;

}