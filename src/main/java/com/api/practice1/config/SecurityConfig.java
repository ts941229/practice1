package com.api.practice1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터를 스프링 필터체인에 등록
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
						.httpBasic().disable()
						.csrf().disable()
						.cors().and()
						.authorizeRequests()
						.antMatchers("/test/**").authenticated()
						.antMatchers("/admin/**").access("hasRole('ADMIN')")
						.antMatchers("/board/**").permitAll()
						.anyRequest().permitAll()
						.and()
						.formLogin()
						.loginPage("/member/member-login-form")
						.and()
						.build();
		
	}
	
}
