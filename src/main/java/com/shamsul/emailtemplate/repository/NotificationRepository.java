package com.shamsul.emailtemplate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shamsul.emailtemplate.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

	Notification findByNotificationTypeAndChannel(String templateName, String channel);

}
