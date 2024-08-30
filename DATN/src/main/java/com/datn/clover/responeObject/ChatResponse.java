package com.datn.clover.responeObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ChatResponse {
    private Integer id;

    private String content;

    private String accountId1;

    private String accountId2;

    private Instant sentDay;

    private GroupchatResponse group;

    private Set<SentimageResponse> sentimages = new LinkedHashSet<>();

}