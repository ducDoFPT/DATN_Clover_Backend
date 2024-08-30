//package com.datn.clover.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Size;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "sentimage")
//public class Sentimage {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Integer id;
//
//    @Size(max = 225)
//    @Column(name = "name", length = 225)
//    private String name;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "chat_id")
//    private Chat chat;
//
//}