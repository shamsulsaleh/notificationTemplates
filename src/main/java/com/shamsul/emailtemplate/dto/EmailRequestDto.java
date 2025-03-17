package com.shamsul.emailtemplate.dto;

import java.util.Map;

import lombok.Data;

@Data
public class EmailRequestDto {

	String[] emailFrom;
	String[] emailTo;
	String[] emailCc;
	String[] emailBcc;
	String subject;
	
	String[] filesName;
	Boolean attmntExist = false;
	
	String templateName;
	String channel;
	
	Map<String, Object> parameters;
}
