package com.api.practice1.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long>{

	@Query("select m from Member m where m.member_email = :member_email")
	Member findByMemberemail(@Param("member_email") String member_email);
	
	
	
}
