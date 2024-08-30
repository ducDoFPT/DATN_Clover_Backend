package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "notifi_day")
    private LocalDate notifiDay;

    @Size(max = 225)
    @Column(name = "content", length = 225)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_notifi")
    @JsonIgnoreProperties(value = "notifications")
    private TypeNotification typeNotifi;

    @ManyToMany(mappedBy = "notifications")
    @JsonIgnoreProperties({"addresses", "account", "role"
            , "notifications", "staff", "shop"
            , "bills", "cart", "comments"
            , "evaluates", "follows1", "follows2"
            , "friends1", "friends2", "interacts"
            , "posts", "promotions", "respondComments"
            , "shares", "shipBills", "storages"
            , "vouchers", "likes"})
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
        account.getNotifications().add(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.getNotifications().remove(this);
    }

}