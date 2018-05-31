package com.abhi.atm.springBootConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
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
public class AtmSpringSecurityConfig implements WebMvcConfigurer {

	private static String REALM = "MY_ATM_REALM";

	@Autowired
	UserDetailsService customUserDetailsService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	// Spring security database authentication using customUserDetailsService.
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("SPRING SECURITY==========>>>> AUTHENTICATION");
		auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	// Basic http based STATE LESS authentication and authorization for admin REST API.
	@Configuration
	@Order(1)
	public static class AdminApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Autowired
		RestBasicAuthenticationEntryPoint restBasicAuthenticationEntryPoint;

		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.antMatcher("/adminRest/**").authorizeRequests().anyRequest().hasAnyRole("ADMIN")
					.and().httpBasic().realmName(REALM).authenticationEntryPoint(restBasicAuthenticationEntryPoint)
					.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and().logout().deleteCookies("JSESSIONID").invalidateHttpSession(true);
		}

		// To allow Pre-flight [OPTIONS] request from browser
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
		}
	}

	// Basic http based STATE LESS authentication and authorization for user REST API.
	@Configuration
	@Order(2)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Autowired
		RestBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;

		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.antMatcher("/rest/**").authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
					.and().httpBasic().realmName(REALM).authenticationEntryPoint(customBasicAuthenticationEntryPoint)
					.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and().logout().deleteCookies("JSESSIONID").invalidateHttpSession(true);
		}

		// To allow Pre-flight [OPTIONS] request from browser
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
		}
	}

	// Form based STATE FULL authentication and authorization for web app.
	@Configuration
	@Order(3)
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.csrf().disable()
					.authorizeRequests()
					.antMatchers("/registerUser/**").permitAll()
					.antMatchers("/secured/**").hasAnyRole("ADMIN")
					.anyRequest().fullyAuthenticated()
					.and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/dashboard").failureUrl("/login")
					.and().logout().permitAll().logoutSuccessUrl("/login").permitAll().deleteCookies("JSESSIONID").invalidateHttpSession(true)
					.and().sessionManagement().invalidSessionUrl("/login");
		}

	}

	// Mapping spring security url to views.
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login.html");
		registry.addViewController("/dashboard").setViewName("index.html");
	}
}