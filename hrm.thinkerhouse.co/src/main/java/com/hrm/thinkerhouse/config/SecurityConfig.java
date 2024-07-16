package com.hrm.thinkerhouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.hrm.thinkerhouse.services.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.authorizeRequests()
			.requestMatchers("/login","/assets/**","/css/**","/img/**","/js/**","/fragments/**","/datainit/**","/error/**").permitAll()
			.requestMatchers("/admin/**","/admin/assets/**","/admin/fragments/**").hasRole("ADMIN")
			.requestMatchers("/backoffice/**").hasRole("USER")
			.requestMatchers("/hrm/**").hasRole("EMPLOYEE")
			.anyRequest().authenticated()
			.and()
			.csrf().disable().formLogin()
			.loginPage("/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login").and()
			.exceptionHandling()
			.accessDeniedPage("/access-denied");
		
		return http.build();
	}
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
		
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
		
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() throws Exception{
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(userDetailsService());
		
		return authenticationProvider;
	}
	
	@Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
	
}
