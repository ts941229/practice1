package com.api.practice1.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "member_seq_generater", sequenceName = "member_seq")
public class Member {

	@Builder(toBuilder = true)
	protected Member(long id, String member_email, String member_password, String member_name, String member_role,
			String member_create_date) {
		super();
		this.id = id;
		this.member_email = member_email;
		this.member_password = member_password;
		this.member_name = member_name;
		this.member_role = member_role;
		this.member_create_date = member_create_date;
	}
	
	@Id
	@GeneratedValue(generator = "member_seq_generater", strategy = GenerationType.IDENTITY)
	private long id;
	
	private String member_email;
	private String member_password;
	private String member_name;
	private String member_role;
	private String member_create_date;
}
