package com.datn.clover.inter;

import com.datn.clover.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationJPA extends JpaRepository<Notification, String> {
}
