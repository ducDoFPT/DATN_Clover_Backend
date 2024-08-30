//package com.datn.clover.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Size;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDate;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "customer_support")
//public class CustomerSupport {
//    @Id
//    @Size(max = 10)
//    @Column(name = "id", nullable = false, length = 10)
//    private String id;
//
//    @Column(name = "support_day")
//    private LocalDate supportDay;
//
//    @Size(max = 50)
//    @Column(name = "fullname", length = 50)
//    private String fullname;
//
//    @Size(max = 10)
//    @Column(name = "phone", length = 10)
//    private String phone;
//
//    @Size(max = 225)
//    @Column(name = "note", length = 225)
//    private String note;
//
//    @Size(max = 10)
//    @Column(name = "account_id", length = 10)
//    private String accountId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "type_sp")
//    private TypeSupport typeSp;
//
//}