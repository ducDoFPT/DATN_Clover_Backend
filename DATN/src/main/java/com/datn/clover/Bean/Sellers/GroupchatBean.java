package com.datn.clover.Bean.Sellers;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class GroupchatBean {
    private Integer id;

    private String accountId1;

    private String accountId2;

    private LocalDate createDay;
    private Set<ChatBean> chats = new LinkedHashSet<>();

}