package com.datn.clover.Bean.Sellers;

import com.datn.clover.entity.Account;
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
public class NotificationBean {
    private Integer id;

    private LocalDate notifiDay;

    private String content;

    private TypeNotificationBean typeNotifi;

    private Set<Account> accounts = new LinkedHashSet<>();

}