package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "type_notification")
public class TypeNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Size(max = 225)
    @Column(name = "note", length = 225)
    private String note;

    @OneToMany(mappedBy = "typeNotifi")
    @JsonIgnoreProperties({"typeNotifi", "accounts"})
    private Set<Notification> notifications = new LinkedHashSet<>();

}