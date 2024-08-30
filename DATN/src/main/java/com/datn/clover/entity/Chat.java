//package com.datn.clover.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Size;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.util.LinkedHashSet;
//import java.util.Set;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "chats")
//public class Chat {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Integer id;
//
//    @Size(max = 225)
//    @Column(name = "content", length = 225)
//    private String content;
//
//    @Size(max = 10)
//    @Column(name = "account_id1", length = 10)
//    private String accountId1;
//
//    @Size(max = 10)
//    @Column(name = "account_id2", length = 10)
//    private String accountId2;
//
//    @Column(name = "sent_day")
//    private LocalDateTime sentDay;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "group_id")
//    private Groupchat group;
//
//    @OneToMany(mappedBy = "chat")
//    private Set<Sentimage> sentimages = new LinkedHashSet<>();
//
//}