package com.abhi.atm.springBootConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Abhishek Patel M N
 *
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	UserDetailsService customUserDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};

	// Spring security database authentication using customUserDetailsService.
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("SPRING SECURITY==========>>>> AUTHENTICATION");
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	// Basic http based based authentication and authorization.
	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			http
					.antMatcher("/api/**")
					.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
					.and().httpBasic()
					.and().csrf().disable();
		}
	}

	// Form based authentication and authorization.
	@Configuration
	@Order(2)
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/secured/**").hasAnyRole("ADMIN")
					.anyRequest().hasAnyRole("ADMIN", "USER")
					.and().formLogin().and().logout().permitAll().logoutSuccessUrl("/login")
					.and().csrf().disable();
		}
	}

}