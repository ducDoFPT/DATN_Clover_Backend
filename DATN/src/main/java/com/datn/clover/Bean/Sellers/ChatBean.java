package com.datn.clover.Bean.Sellers;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ChatBean {
    private Integer id;

    private String content;

    private String accountId1;

    private String accountId2;

    private Instant sentDay;

    private GroupchatBean group;

    private Set<SentimageBean> sentimages = new LinkedHashSet<>();

}