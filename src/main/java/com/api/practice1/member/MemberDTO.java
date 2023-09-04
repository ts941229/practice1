package com.api.practice1.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	
	private long id;
	private String email;
	private String password;
	private String name;
	private String role;
	private String create_date;
	
}
