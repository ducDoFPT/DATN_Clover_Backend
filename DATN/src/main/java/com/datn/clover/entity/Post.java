package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 150)
    @Column(name = "title", length = 150)
    private String title;

    @Column(name = "post_day")
    private Instant postDay;

    @Size(max = 225)
    @Column(name = "content", length = 225)
    private String content;

    @Column(name = "number_likes")
    private Integer numberLikes;

    @Size(max = 20)
    @Column(name = "stype_share", length = 20)
    private String stypeShare;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties({"role", "status", "notifications"
            , "addresses", "bills", "carts"
            , "comments", "evaluates", "interacts"
            , "likes", "posts", "promotions"
            , "respondComments", "shares", "shipBills"
            , "shops", "staff", "storages"
            , "vouchers", "accounts", "account"
            , "acc", "follows1", "follows2"
            , "friends1", "friends2"})
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    @JsonIgnoreProperties({"posts"})
    private PostStatus status;

    @OneToMany(mappedBy = "post")
    @JsonIgnoreProperties({"post", "account", "respondComments"})
    private Set<Comment> comments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "post")
    @JsonIgnoreProperties({"post", "account"})
    private Set<Like> likes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "post")
    @JsonIgnoreProperties({"post"})
    private Set<PostImage> postImages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "post")
    @JsonIgnoreProperties({"post", "account"})
    private Set<Share> shares = new LinkedHashSet<>();

    @OneToMany(mappedBy = "post")
    @JsonIgnoreProperties({"post", "account"})
    private Set<Storage> storages = new LinkedHashSet<>();

}