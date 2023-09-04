package com.api.practice1.member;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		
		String rawPw = memberDTO.getPassword();
		String encodedPw = bCryptPasswordEncoder.encode(rawPw);
		String role = "ROLE_MEMBER";

		// admin일 경우
		if(memberDTO.getEmail().equals("admin")) {role = "ROLE_ADMIN";}
		
		Member member = Member.builder()
												.email(memberDTO.getEmail())
												.name(memberDTO.getName())
												.password(encodedPw)
												.role(role)
												.create_date(Util.getInstance().dateFormat(new Date()))
												.build();

		memberService.save(member);
		
		return "redirect:/member/member-login-form";
	}
	
	@GetMapping("/member-login-form")
	public String memberLoginForm(@RequestParam(value = "error", required = false) String error
													, @RequestParam(value = "exception", required = false) String exception
													, Model model) {
		
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		
		return "/member/memberLogin";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/test")
	public @ResponseBody String test() {
		return "admin 이시군요!";
	}
	
}
