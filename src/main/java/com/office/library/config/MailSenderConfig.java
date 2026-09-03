package com.office.library.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailSenderConfig {

	final private String CLASS_NAME = "[MailSenderConfig] ";
	
	@Bean
	public JavaMailSenderImpl mailSender() {
		System.out.println(CLASS_NAME.concat("mailSender()"));
		
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		mailSenderImpl.setHost("smtp.gmail.com");
		mailSenderImpl.setPort(587);
		mailSenderImpl.setUsername("hohasic@gmail.com");
		mailSenderImpl.setPassword("frgwshxafkeucqis");
		
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		
		mailSenderImpl.setJavaMailProperties(properties);
		
		return mailSenderImpl;
		
	}
	
}
