package com.shamsul.emailtemplate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "notification_template")
public class Notification {

	private static final long serialVersionUID = -3566059123951082870L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "record_id", length = 12)
	private Integer recordId;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "category", length = 50)
	private String category;
	
	@Column(name = "notification_type", length = 50)
	private String notificationType;
	
	@Column(name = "channel", length = 12)
	private String channel;
	
	@Column(name = "subject", length = 100)
	private String subject;
	
	@Column(name = "content", length = 2500)
	private String content;
	
}
