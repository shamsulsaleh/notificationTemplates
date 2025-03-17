package com.shamsul.emailtemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shamsul.emailtemplate.dto.EmailResponseDto;
import com.shamsul.emailtemplate.dto.SmsResponseDto;
import com.shamsul.emailtemplate.services.EmailService;

import io.swagger.v3.oas.annotations.tags.Tag;


@RequestMapping(path = "/email", produces = "application/json")
@RestController
@Tag(name = "Email", description = "Email Controller")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping(value = "/createTemplateAndSendEmail")
	public ResponseEntity<EmailResponseDto> createTemplateAndSendEmail(@RequestParam(value = "emailReq", required = true) String emailReq,
			@RequestParam(value = "attachments", required = false) MultipartFile[] attachments ) {
		EmailResponseDto resp = emailService.createTemplateAndSendEmail(emailReq,attachments);
		return new ResponseEntity<EmailResponseDto>(resp, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/buildEmailTemplate")
	public ResponseEntity<EmailResponseDto> buildEmailTemplate(@RequestParam(value = "emailReq", required = true) String emailReq ) {
		EmailResponseDto resp = emailService.buildEmailTemplate(emailReq);
		return new ResponseEntity<EmailResponseDto>(resp, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/buildSmsTemplate")
	public ResponseEntity<SmsResponseDto> buildSmsTemplate(@RequestParam(value = "smsReq", required = true) String smsReq ) {
		SmsResponseDto resp = emailService.buildSmsTemplate(smsReq);
		return new ResponseEntity<SmsResponseDto>(resp, HttpStatus.CREATED);
	}
}
