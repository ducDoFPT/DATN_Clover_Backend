package com.datn.clover.Bean.Sellers;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SentimageBean {
    private Integer id;

    private String name;

    private ChatBean chat;

}