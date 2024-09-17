package com.datn.clover.services.admin;

import com.datn.clover.entity.Notification;
import com.datn.clover.inter.NotificationJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationJPA notificationJPA;

    public List<Notification> getAllNotifications() {
        return notificationJPA.findAll();
    }
}
