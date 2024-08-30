package com.datn.clover.responeObject;

import com.datn.clover.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class FriendResponse {
    private String id;

    private LocalDate friendDay;

    private Boolean status;

    private Account accountId1;

    private Account accountId2;

}