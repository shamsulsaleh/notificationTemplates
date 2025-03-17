package com.shamsul.emailtemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import({SwaggerConfig.class})
@ComponentScan(basePackages = "com.shamsul.emailtemplate")
public class EmailtemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailtemplateApplication.class, args);
	}
}
