package com.shamsul.emailtemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class SwaggerConfig {
	@Bean
	  public OpenAPI emailTemplateOpenAPI() {
		Contact contact = new Contact();
		contact.setName("Shamsul");
		contact.setEmail("shamsul.saleh@gmail.com");
		return new OpenAPI()
	              .info(new Info().title("Email API").contact(contact)
	              .description("Email operation and templatize email content")
	              .version("0.0.1")
	              .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0")))
	              ;
	  }
}
