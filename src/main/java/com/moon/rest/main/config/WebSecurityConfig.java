package com.moon.rest.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.headers().frameOptions().disable()
		.and()
			.csrf().disable().anonymous()
		.and()
			.httpBasic()
		.and()
			.formLogin()
		.and()
			.logout()
		.and()
			.authorizeRequests()
			.antMatchers("/console/**").permitAll()
		.and()
			.authorizeRequests()
			.antMatchers("/css/**", "/js/**", "/images/**", "/resources/**", "/webjars/**").permitAll()
		.and()
			.authorizeRequests()
			.antMatchers("/").permitAll()
		.and()
			.sessionManagement().invalidSessionUrl("/");
	}
	
}
