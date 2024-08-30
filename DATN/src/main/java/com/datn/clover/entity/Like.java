package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Column(name = "like_day")
    private LocalDate likeDay;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonIgnoreProperties({"account", "comments", "likes"
            , "postImages", "shares", "storages"})
    private Post post;

}