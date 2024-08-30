package com.datn.clover.responeObject;

import com.datn.clover.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class NotificationResponse {
    private Integer id;

    private LocalDate notifiDay;

    private String content;

    private TypeNotificationResponse typeNotifi;

    private Set<Account> accounts = new LinkedHashSet<>();

}