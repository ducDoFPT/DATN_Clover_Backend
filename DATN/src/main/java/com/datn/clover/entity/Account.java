package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

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

    @ManyToOne(fetch = FetchType.LAZY) //Dữ liệu không được tải trực tiếp, thực hiện được khi có yêu cầu
    @JoinColumn(name = "role_id")
    @JsonIgnoreProperties("accounts")
    private Role role;

    @ManyToMany
    @JoinTable(name = "acc_notifi",
            joinColumns = @JoinColumn(name = "acc_id"),
            inverseJoinColumns = @JoinColumn(name = "notifi_id"))
    @JsonIgnoreProperties({"accounts", "typeNotifi"})
    private List<Notification> notifications = new ArrayList<>();

    public void addNotification(Notification notification) {
        notifications.add(notification);
        notification.getAccounts().add(this);
    }

    public void removeNotification(Notification notification) {
        notifications.remove(notification);
        notification.getAccounts().remove(this);
    }

    @OneToOne(mappedBy = "account")
    @JsonIgnoreProperties("account")
    private Address addresses;

    @OneToOne(mappedBy = "account")
    @JsonIgnoreProperties({"account", "shop"})
    private Staff staff;

    @OneToOne(mappedBy = "account")
    @JsonIgnoreProperties({"account", "products","staff"})
    private Shop shop;

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account","voucher","addressBill", "detailBills", "shipBills"})
    private List<Bill> bills ;

    @OneToOne(mappedBy = "account")
    @JsonIgnoreProperties({"account", "prodCarts"})
    private Cart cart;

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account", "post", "respondComments"})
    private List<Comment> comments;

    @OneToMany(mappedBy = "acc")
    @JsonIgnoreProperties({"acc", "prod"})
    private List<Evaluate> evaluates;

    @OneToMany(mappedBy = "accountId1")
    @JsonIgnoreProperties({"accountId1","follows1","accountId2"})
    private List<Follow> follows1;

    @OneToMany(mappedBy = "accountId2")
    @JsonIgnoreProperties({"accountId1","follows2","accountId2"})
    private List<Follow> follows2;

    @OneToMany(mappedBy = "accountId1")
    @JsonIgnoreProperties({"accountId1","friends1","accountId2"})
    private List<Friend> friends1;

    @OneToMany(mappedBy = "accountId2")
    @JsonIgnoreProperties({"accountId2","friends2","accountId1"})
    private List<Friend> friends2;


    @ManyToMany
    @JoinTable(name = "interact_account",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "interact_id"))
    @JsonIgnoreProperties(value = "accounts")
    private List<Interact> interacts = new ArrayList<>();

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
    private List<Like> likes;

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account", "comments", "likes"
                        , "postImages", "shares", "storages"})
    private List<Post> posts;

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account", "products"})
    private List<Promotion> promotions;

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account", "comment"})
    private List<RespondComment> respondComments;

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account","post"})
    private List<Share> shares;

    @OneToMany(mappedBy = "acc")
    @JsonIgnoreProperties({"acc","account","bill"})
    private List<ShipBill> shipBills ;

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account", "post"})
    private List<Storage> storages ;

    @OneToMany(mappedBy = "account")
    @JsonIgnoreProperties({"account", "bills", "tvoucher"})
    private List<Voucher> vouchers;


}