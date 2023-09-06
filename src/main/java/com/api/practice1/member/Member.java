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
	protected Member(long id, String email, String password, String name, String role, String create_date, String provider, String provider_id) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.role = role;
		this.create_date = create_date;
		this.provider = provider;
		this.provider_id = provider_id;
	}
	
	@Id
	@GeneratedValue(generator = "member_seq_generater", strategy = GenerationType.IDENTITY)
	private long id;
	
	private String email;
	private String password;
	private String name;
	private String role;
	private String create_date;
	private String provider;
	private String provider_id;
	
}
