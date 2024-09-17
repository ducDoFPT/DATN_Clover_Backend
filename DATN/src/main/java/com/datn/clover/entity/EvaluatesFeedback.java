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
@Table(name = "evaluates_feedback")
public class EvaluatesFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 150)
    @Column(name = "content", length = 150)
    private String content;

    @Column(name = "evaluate_day")
    private LocalDate evaluateDay;

    @Size(max = 200)
    @Column(name = "image", length = 200)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluate_id")
    @JsonIgnoreProperties({"evaluatesFeedbacks", "prod", "acc"})
    private Evaluate evaluate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    @JsonIgnoreProperties({"evaluatesFeedbacks", "account", "products", "staff"})
    private Shop shop;

}