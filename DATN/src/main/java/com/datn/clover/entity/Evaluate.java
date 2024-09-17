package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "evaluates")
public class Evaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 150)
    @Column(name = "content", length = 150)
    private String content;

    @Column(name = "evaluate_day")
    private LocalDate evaluateDay;

    @Column(name = "star_count")
    private Integer starCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_id")
    @JsonIgnoreProperties({"promotion", "prodType", "shop"
            , "detailBills", "evaluates", "prodCarts"
            , "prodImages", "suppliers", "propertiesValues"})
    private Product prod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acc_id")
    @JsonIgnoreProperties({"role", "status", "notifications"
            , "addresses", "bills", "carts"
            , "comments", "evaluates", "interacts"
            , "likes", "posts", "promotions"
            , "respondComments", "shares", "shipBills"
            , "shops", "staff", "storages"
            , "vouchers", "accounts", "account"
            , "acc", "follows1", "follows2"
            , "friends1", "friends2"})
    private Account acc;

    @OneToMany(mappedBy = "evaluate")
    @JsonIgnoreProperties({"evaluate", "shop"})
    private Set<EvaluatesFeedback> evaluatesFeedbacks = new LinkedHashSet<>();

}