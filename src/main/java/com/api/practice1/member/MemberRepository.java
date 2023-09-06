package com.api.practice1.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long>{

	@Query("select m from Member m where m.email = :email")
	Member findByMemberemail(@Param("email") String email);

	@Query("select m from Member m where m.name = :name")
	Member findByMembername(@Param("name") String name);
	
}
