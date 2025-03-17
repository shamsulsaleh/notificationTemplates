package com.shamsul.emailtemplate.services;

import java.util.List;

import com.shamsul.emailtemplate.model.Notification;

public interface NotificationService {

	Notification findByNotificationTypeAndChannel(String templateName, String channel);

}
