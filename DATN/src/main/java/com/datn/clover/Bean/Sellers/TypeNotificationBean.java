package com.datn.clover.Bean.Sellers;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TypeNotificationBean {
    private String id;

    private String name;

    private String note;

    private Set<NotificationBean> notifications = new LinkedHashSet<>();

}