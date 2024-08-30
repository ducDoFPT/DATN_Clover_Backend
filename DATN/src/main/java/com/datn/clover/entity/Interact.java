package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "interacts")
public class Interact {
    @Id
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Size(max = 150)
    @Column(name = "name", length = 150)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "function_id")
    @JsonIgnoreProperties("interacts")
    private Function function;

    @ManyToMany(mappedBy = "interacts")
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
        account.getInteracts().add(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.getInteracts().remove(this);
    }
}