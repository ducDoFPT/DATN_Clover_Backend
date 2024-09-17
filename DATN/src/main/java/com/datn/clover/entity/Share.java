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
@Table(name = "shares")
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "share_day")
    private LocalDate shareDay;

    @Size(max = 10)
    @Column(name = "receiver", length = 10)
    private String receiver;

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
    @JoinColumn(name = "post_id")
    @JsonIgnoreProperties({"postImages", "account", "status"
            , "comments", "likes", "shares"
            , "storages"})
    private Post post;

}