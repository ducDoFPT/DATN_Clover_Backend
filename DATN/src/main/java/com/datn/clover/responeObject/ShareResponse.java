package com.datn.clover.responeObject;

import com.datn.clover.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ShareResponse {
    private String id;

    private LocalDate shareDay;

    private String receiver;

    private Account account;
    private PostResponse post;

}