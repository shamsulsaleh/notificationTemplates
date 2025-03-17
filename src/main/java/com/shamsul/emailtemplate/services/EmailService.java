package com.shamsul.emailtemplate.services;

import org.springframework.web.multipart.MultipartFile;

import com.shamsul.emailtemplate.dto.EmailResponseDto;
import com.shamsul.emailtemplate.dto.SmsResponseDto;

public interface EmailService {

	EmailResponseDto createTemplateAndSendEmail(String emailObj, MultipartFile[] attachments);

	EmailResponseDto buildEmailTemplate(String emailReq);

	SmsResponseDto buildSmsTemplate(String smsReq);

}
