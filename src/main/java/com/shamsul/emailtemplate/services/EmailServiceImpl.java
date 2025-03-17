package com.shamsul.emailtemplate.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shamsul.emailtemplate.dto.EmailRequestDto;
import com.shamsul.emailtemplate.dto.EmailResponseDto;
import com.shamsul.emailtemplate.dto.SmsRequestDto;
import com.shamsul.emailtemplate.dto.SmsResponseDto;
import com.shamsul.emailtemplate.model.Notification;

@Service
public class EmailServiceImpl implements EmailService {

	private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	private SpringTemplateEngine templateEngine;
	
//	@Autowired
//	private ResourceLoader resourceLoader;
	
	@Value("${app.template.location}")
	private String templateLocation;
	
	@Autowired
	private NotificationService notificationService;
	
	public EmailServiceImpl() {
        templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(stringTemplateResolver());
    }

	private ITemplateResolver stringTemplateResolver() {
        final StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(1));
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

	@Override
	public EmailResponseDto createTemplateAndSendEmail(String emailObj, MultipartFile[] attachments) {
	    //build template
		EmailResponseDto emailResp = this.buildEmailTemplate(emailObj);
	    
	    //send email
		return null;
	}
	
	private String getHtmlContent(String fileName) {
		
		try {
			
			File file = new File (templateLocation + fileName + ".html");
			String content = new String(Files.readAllBytes(file.toPath()));
			return content;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}

	@Override
	public EmailResponseDto buildEmailTemplate(String emailObj) {
		ObjectMapper mapper = new ObjectMapper();
		EmailRequestDto emailReq;
		try {
			emailReq = mapper.readValue(emailObj, EmailRequestDto.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
		String content ="";
		Notification listNotification = notificationService.findByNotificationTypeAndChannel(emailReq.getTemplateName(), emailReq.getChannel());
		if (listNotification != null) {
			content = listNotification.getContent();
			emailReq.setSubject(listNotification.getSubject());
		}
		
		final Context ctx = new Context();
		
		
		for (Map.Entry<String, Object> entry : emailReq.getParameters().entrySet()) {
			ctx.setVariable(entry.getKey(), entry.getValue());
		}
		ctx.setVariable("subscriptionDate", new Date());
		ctx.setVariable("attchmtExist", emailReq.getAttmntExist());
		if (emailReq.getFilesName() != null && emailReq.getFilesName().length > 0) {
			ctx.setVariable("files", emailReq.getFilesName());
		} else {
			ctx.setVariable("files", new String[] {});
		}
		final String bodyContent = this.templateEngine.process(content, ctx);
		
		String subject = "";
		if (StringUtils.isNotEmpty(emailReq.getSubject()) ) {
			subject = this.templateEngine.process(emailReq.getSubject(), ctx);
		}
		
	    final String headerContent = this.getHtmlContent("header");
	    final String footerContent = this.getHtmlContent("footer");
	    StringBuilder combineContent = new StringBuilder(headerContent);
	    combineContent.append(bodyContent).append(footerContent);
	    
//	    logger.info(combineContent.toString());
//	    logger.info(subject);
	    
	    EmailResponseDto resp = new EmailResponseDto();
	    resp.setChannel(emailReq.getChannel());
	    resp.setEmailBcc(emailReq.getEmailBcc());
	    resp.setEmailCc(emailReq.getEmailCc());
	    resp.setEmailFrom(emailReq.getEmailFrom());
	    resp.setEmailTo(emailReq.getEmailTo());
	    resp.setFilesName(emailReq.getFilesName());
	    resp.setSubject(subject);
	    resp.setTemplateName(emailReq.getTemplateName());
	    
	    resp.setContent(combineContent.toString());
		return resp;
	}

	@Override
	public SmsResponseDto buildSmsTemplate(String smsReqStr) {
		ObjectMapper mapper = new ObjectMapper();
		SmsRequestDto smsReq;
		try {
			smsReq = mapper.readValue(smsReqStr, SmsRequestDto.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
		
		Notification listNotification = notificationService.findByNotificationTypeAndChannel(smsReq.getTemplateName(), smsReq.getChannel());
		String content = "";
		if (listNotification != null) {
			content = listNotification.getContent();
		}
		
		final Context ctx = new Context();
		for (Map.Entry<String, Object> entry : smsReq.getParameters().entrySet()) {
			ctx.setVariable(entry.getKey(), entry.getValue());
		}
		
		String contentResult = "";
		if (StringUtils.isNotEmpty(content) ) {
			contentResult = this.templateEngine.process(content, ctx);
		}
		
		SmsResponseDto resp = new SmsResponseDto();
		resp.setChannel(smsReq.getChannel());
		resp.setContent(contentResult);
		resp.setSmsFrom(smsReq.getSmsFrom());
		resp.setSmsTo(smsReq.getSmsTo());
		resp.setTemplateName(smsReq.getTemplateName());
		
		return resp;
	}
	
	
	
}
