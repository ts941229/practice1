package com.api.practice1.config.oauth;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.api.practice1.config.auth.PrincipalDetails;
import com.api.practice1.config.oauth.provider.FacebookUserInfo;
import com.api.practice1.config.oauth.provider.GoogleUserInfo;
import com.api.practice1.config.oauth.provider.KakaoUserInfo;
import com.api.practice1.config.oauth.provider.NaverUserInfo;
import com.api.practice1.config.oauth.provider.OAuth2UserInfo;
import com.api.practice1.global.Util;
import com.api.practice1.member.Member;
import com.api.practice1.member.MemberRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{

	@Autowired
    private MemberRepository memberRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// 구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		OAuth2UserInfo oAuth2UserInfo = null;

		System.out.println("getAttributes : "+oAuth2User.getAttributes());
		
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			// 구글 로그인시
			oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			// 페이스북 로그인시
			oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			// 네이버 로그인시
			oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
			// 카카오 로그인시
			oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
		}else {
			System.out.println("구글 , 페이스북, 네이버, 카카오 로그인만 지원됩니다.");
		}
		
		String provider = oAuth2UserInfo.getProvider(); // google
		String provider_id = oAuth2UserInfo.getProviderId(); // 12030124512
		String name = provider+"_"+provider_id; // google_2139149202
		String email = oAuth2UserInfo.getEmail(); 
		String password = bCryptPasswordEncoder.encode("UUID넣을것을추천");
		String role = "ROLE_USER";
		String create_date = Util.getInstance().dateFormat(new Date());
		
		Member member = memberRepository.findByMembername(name);
		
		if(member == null) {
			System.out.println("로그인이 최초입니다. 해당 소셜 아이디로 자동 회원가입 됩니다.");
			member = Member.builder()
										.email(email)
										.name(name)
										.password(password)
										.role(role)
										.create_date(create_date)
										.provider(provider)
										.provider_id(provider_id)
										.build();
			memberRepository.save(member);
		}else {
			System.out.println("이미 가입된 회원입니다.");
		}
		
		return new PrincipalDetails(member, oAuth2User.getAttributes());
	}
	
}
