package com.abhi.atm.springBootConfiguration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Abhishek Patel M N Jan 18, 2018
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan("com.abhi")
@EntityScan("com.abhi")
@EnableJpaRepositories("com.abhi")
public class Application extends SpringBootServletInitializer {

	// Spring boot configuration to use wildfly.
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.out.println("======== inside Spring boot configure method ==========");
		return application.sources(Application.class);
	}

	// Main method
	public static void main(String[] args) {
		System.out.println("======== inside Mian method ==========");
		SpringApplication.run(Application.class, args);
	}

	// ModelMapper used for convertin DTO to entity and viceversa.
	@Bean
	public ModelMapper modelMapper() {
		System.out.println("======== Creating Model Map object ==========");
		return new ModelMapper();
	}

	// Session listner for create and delete
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.addListener(new SessionListener());
	}
	
	// Password encryptor and decryptor.
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};
}
