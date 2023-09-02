package com.api.practice1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String mainPage() {
		return "/main/index";
	}
	
	@GetMapping("/test/test")
	public String testPage() {
		return "/main/test";
	}
	
}
