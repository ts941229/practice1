package com.api.practice1.config;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

// 시큐리티 login 실패시 url에 error만 나오고 에러정보가 나오지 않아
// 어떤 에러가 왜 일어났는지 직관적으로 알기 힘들기 때문에
// 로그인 실패를 핸들링할 수 있는 클래스를 만들어 에러를 띄워보자
// config의 failureHandler 메소드의 파라미터로 AuthenticationFailureHandler를 넣어줘야 하기 때문에
// 아래와 같은 클래스를 상속한다 (SimpleUrlAuthenticationFailureHandler는 setDefaultFailureUrl 메소드를 사용하기 위함)
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler{
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		System.out.println("login failure handler");
		
		String errorMessage;
		
		if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException ) {
			errorMessage = "아이디 또는 비밀번호가 맞지 않습니다.";
		}else if(exception instanceof UsernameNotFoundException) {
			errorMessage = "존재하지 않는 아이디 입니다.";
		}else {
			errorMessage = "알 수 없는 이유로 로그인 되지 않습니다.";
		}
		
		errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
		setDefaultFailureUrl("/member/member-login-form?error=true&exception="+errorMessage);
		super.onAuthenticationFailure(request, response, exception);
	}
	
}
