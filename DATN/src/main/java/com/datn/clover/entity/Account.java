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
@Table(name = "accounts")
@JsonIgnoreProperties({"password","username"})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Column(name = "username", length = 20)
    private String username;

    @Size(max = 20)
    @Column(name = "password", length = 20)
    private String password;

    @Size(max = 50)
    @Column(name = "fullname", length = 50)
    private String fullname;

    @Column(name = "gender")
    private Boolean gender;

    @Size(max = 10)
    @Column(name = "phone", length = 10)
    private String phone;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

    @Size(max = 100)
    @Column(name = "avatar", length = 100)
    private String avatar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @JsonIgnoreProperties({"accounts"})
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    @JsonIgnoreProperties({"accounts"})
    private AccountStatus status;

    @ManyToMany(mappedBy = "accounts")
    @JsonIgnoreProperties({"accounts", "typeNotifi"})
    private Set<Notification> notifications = new LinkedHashSet<>();

    public void addNotification(Notification notification) {
        notifications.add(notification);
        notification.getAccounts().add(this);
    }

    public void removeNotification(Notification notification) {
        notifications.remove(notification);
        notification.getAccounts().remove(this);
    }

    @OneToOne(mappedBy = "account")
    @JsonIgnoreProperties({"account"})
    private Address addresses;

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"addressBill", "account", "voucher"
            , "status", "detailBills", "shipBills"})
    private Set<Bill> bills = new LinkedHashSet<>();

    @OneToOne(mappedBy = "account")
    @JsonIgnoreProperties({"account", "prodCarts"})
    private Cart carts;

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account", "post", "respondComments"})
    private Set<Comment> comments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "acc")
    @JsonIgnoreProperties({"prod", "acc", "evaluatesFeedbacks"})
    private Set<Evaluate> evaluates = new LinkedHashSet<>();

    @OneToMany(mappedBy = "accountId1")
    @JsonIgnoreProperties({"accountId1", "accountId2"})
    private Set<Follow> follows1 = new LinkedHashSet<>();

    @OneToMany(mappedBy = "accountId2")
    @JsonIgnoreProperties({"accountId1", "accountId2"})
    private Set<Follow> follows2 = new LinkedHashSet<>();

    @OneToMany(mappedBy = "accountId1")
    @JsonIgnoreProperties({"accountId1", "accountId2"})
    private Set<Friend> friends1 = new LinkedHashSet<>();

    @OneToMany(mappedBy = "accountId2")
    @JsonIgnoreProperties({"accountId1", "accountId2"})
    private Set<Friend> friends2 = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "accounts")
    @JsonIgnoreProperties({"accounts", "function"})
    private Set<Interact> interacts = new LinkedHashSet<>();

    public void addInteract(Interact interact) {
        interacts.add(interact);
        interact.getAccounts().add(this);
    }

    public void removeInteract(Interact interact) {
        interacts.remove(interact);
        interact.getAccounts().remove(this);
    }

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account", "post"})
    private Set<Like> likes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"postImages", "account", "status"
            , "comments", "likes", "shares"
            , "storages"})
    private Set<Post> posts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account", "products"})
    private Set<Promotion> promotions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account", "comment"})
    private Set<RespondComment> respondComments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account", "post"})
    private Set<Share> shares = new LinkedHashSet<>();

    @OneToMany(mappedBy = "acc")
    @JsonIgnoreProperties({"acc", "bill"})
    private Set<ShipBill> shipBills = new LinkedHashSet<>();

    @OneToOne(mappedBy = "account")
    @JsonIgnoreProperties({"account", "evaluatesFeedbacks", "products", "staff"})
    private Shop shops;

    @OneToOne(mappedBy = "account")
    @JsonIgnoreProperties({"account", "shop"})
    private Staff staff;

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account", "post"})
    private Set<Storage> storages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account", "tvoucher", "bills"})
    private Set<Voucher> vouchers = new LinkedHashSet<>();

}