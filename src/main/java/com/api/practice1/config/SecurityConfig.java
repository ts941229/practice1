package com.api.practice1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.api.practice1.config.oauth.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터를 스프링 필터체인에 등록
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // @Secured , @prePostAuthorize를 간편하게 설정할 수 있다
public class SecurityConfig {
	
	@Autowired
	PrincipalOauth2UserService principalOauth2UserService;
	
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
		
		httpSecurity.httpBasic().disable()
							.csrf().disable()
							.cors();

		httpSecurity.authorizeRequests()
							.antMatchers("/test/**").authenticated() // /test/** 모든 페이지를 인증진행
							.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // /admin/** 모든 페이지의 role 접근권한 설정
							.antMatchers("/board/**").permitAll() // /board/** 모든 페이지 접근 허락
							.anyRequest().permitAll();
		
		httpSecurity.formLogin()
							.loginPage("/member/member-login-form") // 로그인 필요시 해당 페이지로 이동시킴
							.loginProcessingUrl("/member/member-login") // login 호출시 시큐리티가 낚아 채서 로그인 진행
							.usernameParameter("email") // username 파라미터 (default : username) 파라미터 name값이 username이 아닌경우 설정해줘야함
							.defaultSuccessUrl("/") // 로그인 완료시 갈 페이지
							.failureHandler(loginFailHandler()); // 로그인 실패시 실패 핸들링
		
		httpSecurity.oauth2Login()
							.loginPage("/member/member-login-form") // 구글 로그인이 완료된 뒤의 후처리 필요 
							.userInfoEndpoint()
							.userService(principalOauth2UserService);
		
		return httpSecurity.build();
	}
	
}
