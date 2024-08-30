package com.datn.clover.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "post_images")
public class PostImage {
    @Id
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Size(max = 150)
    @Column(name = "name_image", length = 150)
    private String nameImage;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnoreProperties({"account", "comments", "likes"
            , "postImages", "shares", "storages"})
    private Post post;

}