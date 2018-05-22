package com.abhi.atm.springBootConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Abhishek Patel M N
 *
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AtmSecurityConfig implements WebMvcConfigurer {

	private static String REALM = "MY_ATM_REALM";

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

	// Basic http based STATE LESS authentication and authorization for REST API.
	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/api/**").authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
					.and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
					.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and().logout().deleteCookies("JSESSIONID").invalidateHttpSession(true)
					.and().csrf().disable();
		}

		@Bean
		public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
			return new CustomBasicAuthenticationEntryPoint();
		}

		// To allow Pre-flight [OPTIONS] request from browser
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
		}
	}

	// Form based authentication and authorization for web app.
	@Configuration
	@Order(2)
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers("/registerUser/**").permitAll()
					.antMatchers("/secured/**").hasAnyRole("ADMIN")
					.anyRequest().hasAnyRole("ADMIN", "USER")
					.anyRequest().fullyAuthenticated()
					.and().formLogin().loginPage("/login").defaultSuccessUrl("/dashboard").failureUrl("/login")
					.and().logout().permitAll().logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
					.and().csrf().disable();
		}
	}

	// Mapping spring security url to views.
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("index.html");
		registry.addViewController("/dashboard").setViewName("dashboard.html");
	}
}