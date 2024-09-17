package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "respond_comments")
public class RespondComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 150)
    @Column(name = "content", length = 150)
    private String content;

    @Column(name = "respond_day")
    private Instant respondDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    @JsonIgnoreProperties({"respondComments", "post", "account"})
    private Comment comment;

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

}