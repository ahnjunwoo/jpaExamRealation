package com.zerock.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.zerock.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String>{

	@Query("SELECT m.uid, count(p) from Member m LEFT OUTER JOIN Profile p"+
	" ON m.uid = p.member where m.uid = ?1 GROUP BY m")
	List<Object[]> getMemberWithProfileCount(String string);
	
	@Query("SELECT m, p from Member m LEFT OUTER JOIN Profile p"+
			" ON m.uid = p.member where m.uid = ?1 AND p.current = true")
	List<Object[]> getMemberWithProfile(String string);

}
