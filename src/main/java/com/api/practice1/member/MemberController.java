package com.api.practice1.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

	@GetMapping("/member-regist-form")
	public String memberRegistForm() {
		
		System.out.println("member regist form");
		
		return "/member/memberRegist";
	}
}
