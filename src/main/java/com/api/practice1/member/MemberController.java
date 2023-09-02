package com.api.practice1.member;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.practice1.global.Util;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/member-regist-form")
	public String memberRegistForm() {
		return "/member/memberRegist";
	}
	
	@PostMapping("/member-regist")
	public String memberRegist(@ModelAttribute MemberDTO memberDTO) {
		
		String rawPw = memberDTO.getMember_password();
		String encodedPw = bCryptPasswordEncoder.encode(rawPw);
		String role = "ROLE_MEMBER";

		// admin일 경우
		if(memberDTO.getMember_email().equals("admin")) {role = "ROLE_ADMIN";}
		
		Member member = Member.builder()
												.member_email(memberDTO.getMember_email())
												.member_name(memberDTO.getMember_name())
												.member_password(encodedPw)
												.member_role(role)
												.member_create_date(Util.getInstance().dateFormat(new Date()))
												.build();

		memberService.save(member);
		
		return "redirect:/member/member-login-form";
	}
	
	@GetMapping("/member-login-form")
	public String memberLoginForm() {
		return "/member/memberLogin";
	}
	
}
