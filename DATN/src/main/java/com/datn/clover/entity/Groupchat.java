//package com.datn.clover.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Size;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDate;
//import java.util.LinkedHashSet;
//import java.util.Set;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "groupchats")
//public class Groupchat {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Integer id;
//
//    @Size(max = 10)
//    @Column(name = "account_id1", length = 10)
//    private String accountId1;
//
//    @Size(max = 10)
//    @Column(name = "account_id2", length = 10)
//    private String accountId2;
//
//    @Column(name = "create_day")
//    private LocalDate createDay;
//
//    @OneToMany(mappedBy = "group")
//    private Set<Chat> chats = new LinkedHashSet<>();
//
//}