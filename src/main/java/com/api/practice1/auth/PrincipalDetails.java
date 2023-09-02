package com.api.practice1.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.practice1.member.Member;
import com.api.practice1.member.MemberDTO;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다. (config의 loginProccessingURL 메서드)
// 로그인 진행이 완료되면 시큐리티 session을 만들어준다 (Security ContextHolder)
// 오브젝트 => Authentication 타입 객체
// Authentication 안에 User정보가 있어야 된다
// User오브젝트 타입 => UserDetails타입 객체

// Security Session => Authentication => UserDetails

public class PrincipalDetails implements UserDetails{

	private Member member;
	
	public PrincipalDetails(Member member) {
		this.member = member;
	}
	
	// 해당 유저의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<GrantedAuthority>();
		
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return member.getMember_role();
			}
		});
		
		return collect;
	}

	@Override
	public String getPassword() {
		return member.getMember_password();
	}

	@Override
	public String getUsername() {
		return member.getMember_name();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		// ex) 사이트의 마지막 로그인 시간이 1년이 지나면 휴면계정 변환
		// member 엔티티에 last_login_date와 같은 컬럼 추가해주고
		// 현재시간 - last_login_date > 1년 이라면 return fasle;
		
		return true;
	}
	
}
