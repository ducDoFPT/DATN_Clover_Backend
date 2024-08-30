package com.datn.clover.responeObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TypeNotificationResponse {
    private String id;

    private String name;

    private String note;

    private Set<NotificationResponse> notifications = new LinkedHashSet<>();

}