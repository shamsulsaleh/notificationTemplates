package com.shamsul.emailtemplate.dto;

import lombok.Data;

@Data
public class EmailResponseDto {
	String[] emailFrom;
	String[] emailTo;
	String[] emailCc;
	String[] emailBcc;
	String subject;
	
	String[] filesName;

	String templateName;
	String channel;

	String content;
}
