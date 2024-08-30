package com.datn.clover.responeObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class GroupchatResponse {
    private Integer id;

    private String accountId1;

    private String accountId2;

    private LocalDate createDay;
    private Set<ChatResponse> chats = new LinkedHashSet<>();

}