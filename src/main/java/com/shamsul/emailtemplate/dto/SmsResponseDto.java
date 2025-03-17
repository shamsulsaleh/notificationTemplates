package com.shamsul.emailtemplate.dto;

import lombok.Data;

@Data
public class SmsResponseDto {
	String templateName;
	String channel;
	
	String[] smsTo;
	String[] smsFrom;
	
	String content;
}
