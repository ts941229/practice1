package com.api.practice1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String mainPage() {
		System.out.println("mainForm 들어옴");
		return "/main/index";
	}
	
}
