package com.datn.clover.responeObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SentimageResponse {
    private Integer id;

    private String name;

    private ChatResponse chat;

}