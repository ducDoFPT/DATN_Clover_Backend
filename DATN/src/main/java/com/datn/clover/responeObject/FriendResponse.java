package com.datn.clover.responeObject;

import lombok.Data;

import java.time.LocalDate;
@Data
public class FriendResponse {
    private Integer id;

    private LocalDate friendDay;

    private Boolean status;

}
