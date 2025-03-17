package com.shamsul.emailtemplate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shamsul.emailtemplate.model.Notification;
import com.shamsul.emailtemplate.repository.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepository notificationRepo;

	@Override
	public Notification findByNotificationTypeAndChannel(String templateName, String channel) {
		Notification notificationList = notificationRepo.findByNotificationTypeAndChannel(templateName,channel);
		return notificationList;
	}
	
}
