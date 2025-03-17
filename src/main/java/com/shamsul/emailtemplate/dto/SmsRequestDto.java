package com.shamsul.emailtemplate.dto;

import java.util.Map;

import lombok.Data;

@Data
public class SmsRequestDto {
	String templateName;
	String channel;
	
	String[] smsTo;
	String[] smsFrom;
	
	Map<String, Object> parameters;
}
