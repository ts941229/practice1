package com.api.practice1.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	
	private long id;
	private String member_email;
	private String member_password;
	private String member_name;
	private String member_role;
	private String member_create_date;
	
}
