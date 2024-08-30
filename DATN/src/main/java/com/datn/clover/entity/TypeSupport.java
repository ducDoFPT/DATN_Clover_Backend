//package com.datn.clover.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Size;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.LinkedHashSet;
//import java.util.Set;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "type_support")
//public class TypeSupport {
//    @Id
//    @Size(max = 10)
//    @Column(name = "id", nullable = false, length = 10)
//    private String id;
//
//    @Size(max = 50)
//    @Column(name = "name", length = 50)
//    private String name;
//
//    @Size(max = 50)
//    @Column(name = "scale", length = 50)
//    private String scale;
//
//    @Size(max = 225)
//    @Column(name = "note", length = 225)
//    private String note;
//
//    @OneToMany(mappedBy = "typeSp")
//    private Set<CustomerSupport> customerSupports = new LinkedHashSet<>();
//
//}