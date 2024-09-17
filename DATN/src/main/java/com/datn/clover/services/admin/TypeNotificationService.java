package com.datn.clover.services.admin;

import com.datn.clover.entity.TypeNotification;
import com.datn.clover.inter.TypeNotificationJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeNotificationService {

    @Autowired
    private TypeNotificationJPA typeNotificationJPA;

    public List<TypeNotification> getAllTypeNotifications() {
        return typeNotificationJPA.findAll();
    }
}
