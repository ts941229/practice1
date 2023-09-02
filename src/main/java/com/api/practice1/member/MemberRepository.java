package com.api.practice1.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long>{

	@Query("select m from Member m where m.member_name = :member_name")
	Member findByMembername(@Param("member_name") String member_name);
	
	
	
}
