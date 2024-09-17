package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
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

    @Size(max = 150)
    @Column(name = "title", length = 150)
    private String title;

    @Size(max = 225)
    @Column(name = "content", length = 225)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_notifi")
    @JsonIgnoreProperties({"notifications"})
    private TypeNotification typeNotifi;

    @Column(name = "send_day")
    private Instant sendDay;

    @ManyToMany
    @JoinTable(name = "acc_notifi",
            joinColumns = @JoinColumn(name = "acc_id"),
            inverseJoinColumns = @JoinColumn(name = "notifi_id"))
    @JsonIgnoreProperties({"role", "status", "notifications"
            , "addresses", "bills", "carts"
            , "comments", "evaluates", "interacts"
            , "likes", "posts", "promotions"
            , "respondComments", "shares", "shipBills"
            , "shops", "staff", "storages"
            , "vouchers", "accounts", "account"
            , "acc", "follows1", "follows2"
            , "friends1", "friends2"})
    private Set<Account> accounts = new LinkedHashSet<>();

    public void addAccount(Account account) {
        accounts.add(account);
        account.getNotifications().add(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.getNotifications().remove(this);
    }

}