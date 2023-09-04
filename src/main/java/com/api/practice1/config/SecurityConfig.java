package com.api.practice1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터를 스프링 필터체인에 등록
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder encodePw() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public LoginFailHandler loginFailHandler() {
		return new LoginFailHandler();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
						.httpBasic().disable()
						.csrf().disable()
						.cors().and()
						.authorizeRequests()
						.antMatchers("/test/**").authenticated() // /test/** 모든 페이지를 인증진행
						.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // /admin/** 모든 페이지의 role 접근권한 설정
						.antMatchers("/board/**").permitAll() // /board/** 모든 페이지 접근 허락
						.anyRequest().permitAll()
						.and()
						.formLogin()
						.loginPage("/member/member-login-form") // 로그인 필요시 해당 페이지로 이동시킴
						.loginProcessingUrl("/member/member-login") // login 호출시 시큐리티가 낚아 채서 로그인 진행
						.usernameParameter("member_email") // username 파라미터 (default : username) 파라미터 name값이 username이 아닌경우 설정해줘야함
						.defaultSuccessUrl("/") // 로그인 완료시 갈 페이지
						.failureHandler(loginFailHandler()) // 로그인 실패시 실패 핸들링
						.and()
						.build();
	}
	
}
