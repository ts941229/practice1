package com.api.practice1.config.oauth.provider;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo{

	private Map<String, Object> attributes;
	
	public KakaoUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public String getProvider() {
		return "kakao";
	}

	@Override
	public String getProviderId() {
		return attributes.get("id")+"";
	}

	@Override
	public String getEmail() {
		Map<String, Object> kakao_account = (Map) attributes.get("kakao_account");
		return (String) kakao_account.get("email");
	}

	@Override
	public String getName() {
		Map<String, Object> properties = (Map) attributes.get("properties");
		return (String) properties.get("nickname");
	}
	
}
